package generator.service.impl;

import aks.com.sdk.exception.ServiceException;
import aks.com.sdk.resp.HttpCode;
import aks.com.sdk.util.md5.MD5Utils;
import aks.com.web.domain.common.req.*;
import aks.com.web.domain.common.vo.UserInfoVo;
import aks.com.web.domain.common.vo.UserVo;
import aks.com.web.enums.RoleEnum;
import aks.com.web.enums.StatusEnum;
import aks.com.web.event.UserRegistrationEvent;
import aks.com.web.util.CaptchaUtils;
import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import generator.domain.MembershipLevels;
import generator.domain.UserMemberships;
import generator.domain.UserPoints;
import generator.domain.Users;
import generator.mapper.MembershipLevelsMapper;
import generator.mapper.UserMembershipsMapper;
import generator.mapper.UserPointsMapper;
import generator.service.UsersService;
import generator.mapper.UsersMapper;
import jakarta.annotation.Resource;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Date;

/**
* @author 34011
* @description 针对表【users】的数据库操作Service实现
* @createDate 2025-05-26 13:23:18
*/
@Service
public class UsersServiceImpl extends ServiceImpl<UsersMapper, Users>
    implements UsersService{

    @Resource
    private UsersMapper usersMapper;

    @Resource
    private CaptchaUtils captchaUtils;

    @Resource
    private UserPointsMapper userPointsMapper;

    @Resource
    private UserMembershipsMapper userMembershipsMapper;

    @Resource
    private MembershipLevelsMapper membershipLevelsMapper;

    @Resource
    private ApplicationEventPublisher eventPublisher;

    @Override
    public UserVo login(String username, String password) {
        password = MD5Utils.encrypt(password);
        Users users = usersMapper.selectOne(new LambdaQueryWrapper<Users>()
                .eq(Users::getUsername, username)
                .eq(Users::getPassword, password)
                .eq(Users::getStatus, StatusEnum.NORMAL.getCode())
        );
        if (users == null) {
            throw new ServiceException(HttpCode.USERNAME_PASSWORD_ERROR);
        }
        StpUtil.login(users.getId());
        return UserVo.builder()
                .id(users.getId())
                .username(users.getUsername())
                .email(users.getEmail())
                .fullName(users.getFullName())
                .phone(users.getPhone())
                .role(users.getRole())
                .token(StpUtil.getTokenValue())
                .build();
    }

    @Override
    public Boolean register(UserReq userReq) {
        if (!StringUtils.hasText(userReq.getCaptchaKey()) || !StringUtils.hasText(userReq.getCaptchaCode())) {
            throw new ServiceException(HttpCode.CAPTCHA_ERROR);
        }

        // 验证图形验证码
        captchaUtils.validateCaptchaWithException(userReq.getCaptchaKey(), userReq.getCaptchaCode());

        // 验证邮箱验证码，同时确保验证码与提交的邮箱匹配
        captchaUtils.validateEmailCaptchaWithException(
            userReq.getEmailCaptchaKey(),
            userReq.getEmailCaptchaCode(),
            userReq.getEmail()
        );

        // 检查用户名是否已存在
        Users existingUser = usersMapper.selectOne(new LambdaQueryWrapper<Users>()
                .eq(Users::getUsername, userReq.getUsername())
        );

        if (existingUser != null) {
            throw new ServiceException("用户名已存在", HttpCode.INTERNAL_SERVER_ERROR.getCode());
        }

        // 检查邮箱是否已存在
        Users existingEmail = usersMapper.selectOne(new LambdaQueryWrapper<Users>()
                .eq(Users::getEmail, userReq.getEmail())
        );

        if (existingEmail != null) {
            throw new ServiceException("邮箱已被注册", HttpCode.INTERNAL_SERVER_ERROR.getCode());
        }

        // 密码加密
        userReq.setPassword(MD5Utils.encrypt(userReq.getPassword()));

        return createNewUser(userReq);
    }

    @Override
    public UserVo getUser(Long loginId) {
        Users user = usersMapper.selectById(loginId);
        return UserVo.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .fullName(user.getFullName())
                .phone(user.getPhone())
                .role(user.getRole())
                .token(StpUtil.getTokenValue())
                .build();
    }

    @Override
    public Boolean update(UserUpdateReq userUpdateReq) {
        var user = usersMapper.selectById((Long)StpUtil.getLoginId());
        user.setFullName(userUpdateReq.getFullName());
        user.setPhone(userUpdateReq.getPhone());
        user.setUpdatedAt(new java.util.Date());
        return usersMapper.updateById(user) > 0;
    }

    @Override
    public Boolean updateEmail(UpdateEmailReq updateEmailReq, Long userId) {
        captchaUtils.validateEmailCaptchaWithException(
            updateEmailReq.getEmailCaptchaKey(),
            updateEmailReq.getEmailCaptchaCode(),
            updateEmailReq.getEmail()
        );
        Users existingEmail = usersMapper.selectOne(new LambdaQueryWrapper<Users>()
                .eq(Users::getEmail, updateEmailReq.getEmail())
                .ne(Users::getId, userId)
        );

        if (existingEmail != null) {
            throw new ServiceException("邮箱已被注册", HttpCode.INTERNAL_SERVER_ERROR.getCode());
        }

        // 更新用户邮箱
        Users user = new Users();
        user.setId(userId);
        user.setEmail(updateEmailReq.getEmail());
        user.setUpdatedAt(new java.util.Date());

        return usersMapper.updateById(user) > 0;
    }

    @Override
    public Boolean resetPassword(ResetPasswordReq resetPasswordReq) {
        captchaUtils.validateEmailCaptchaWithException(
            resetPasswordReq.getEmailCaptchaKey(),
            resetPasswordReq.getEmailCaptchaCode(),
            resetPasswordReq.getEmail()
        );
        Users user = usersMapper.selectOne(new LambdaQueryWrapper<Users>()
                .eq(Users::getEmail, resetPasswordReq.getEmail())
                .eq(Users::getStatus, StatusEnum.NORMAL.getCode())
        );

        if (user == null) {
            throw new ServiceException("该邮箱未注册", HttpCode.INTERNAL_SERVER_ERROR.getCode());
        }

        user.setPassword(MD5Utils.encrypt(resetPasswordReq.getNewPassword()));
        user.setUpdatedAt(new java.util.Date());

        return usersMapper.updateById(user) > 0;
    }

    @Override
    public UserInfoVo getUserInfoById(Long id) {
        Users user = usersMapper.selectById(id);

        if (user == null) {
            throw new ServiceException("用户不存在", HttpCode.INTERNAL_SERVER_ERROR.getCode());
        }

        UserPoints userPoints = userPointsMapper.selectOne(new LambdaQueryWrapper<UserPoints>()
                .eq(UserPoints::getUserId, id)
        );

        if (userPoints == null) {
            throw new ServiceException("用户不存在", HttpCode.INTERNAL_SERVER_ERROR.getCode());
        }

        UserMemberships userMemberships = userMembershipsMapper.selectOne(new LambdaQueryWrapper<UserMemberships>()
                .eq(UserMemberships::getUserId, id)
        );

        long levelId = 0;


        if (userMemberships != null) {
            levelId = userMemberships.getLevelId();
        }

        return UserInfoVo.builder()
                .id(user.getId())
                .username(user.getUsername())
                .password(user.getPassword())
                .email(user.getEmail())
                .fullName(user.getFullName())
                .phone(user.getPhone())
                .status(user.getStatus())
                .role(user.getRole())
                .points(userPoints.getBalance())
                .totalEarned(userPoints.getTotalEarned())
                .totalConsumed(userPoints.getTotalConsumed())
                .membershipLevelId(levelId)
                .membershipExpireTime(userMemberships == null ? null : userMemberships.getExpiryDate())
                .build();
    }

    private boolean createNewUser(UserReq userReq) {

        // 创建新用户
        Users user = new Users();
        user.setUsername(userReq.getUsername());
        user.setPassword(userReq.getPassword());
        user.setEmail(userReq.getEmail());
        user.setFullName(userReq.getFullName());
        user.setPhone(userReq.getPhone());
        user.setRole(RoleEnum.USER.getRole());
        user.setStatus(StatusEnum.NORMAL.getCode());
        user.setIsDeleted(0);
        user.setCreatedAt(new java.util.Date());
        user.setUpdatedAt(new java.util.Date());

        // 保存用户
        int result = usersMapper.insert(user);

        // 发布用户注册事件
        if (result > 0) {
            eventPublisher.publishEvent(new UserRegistrationEvent(this, user));
        }

        return result > 0;
    }

    private boolean createNewUser(AddUserReq userReq) {

        // 创建新用户
        Users user = new Users();
        user.setUsername(userReq.getUsername());
        user.setPassword(userReq.getPassword());
        user.setEmail(userReq.getEmail());
        user.setFullName(userReq.getFullName());
        user.setPhone(userReq.getPhone());
        user.setRole(userReq.getRole());
        user.setStatus(StatusEnum.NORMAL.getCode());
        user.setIsDeleted(0);
        user.setCreatedAt(new java.util.Date());
        user.setUpdatedAt(new java.util.Date());

        // 保存用户
        int result = usersMapper.insert(user);

        return result > 0;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean updateUserInfo(UserInfoReq userInfoReq) {
        validateUserInfoRequest(userInfoReq);

        // 更新用户基本信息
        updateBasicUserInfo(userInfoReq);

        // 更新会员信息
        updateUserMembership(userInfoReq);

        // 更新用户积分（如果提供了积分信息）
        if (userInfoReq.getPoints() != null) {
            updateUserPoints(userInfoReq);
        }

        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean deleteUserInfo(Long id) {
        if (id == null) {
            throw new ServiceException("用户ID不能为空", HttpCode.INTERNAL_SERVER_ERROR.getCode());
        }

        // 检查用户是否存在
        Users existingUser = usersMapper.selectById(id);
        if (existingUser == null) {
            throw new ServiceException("用户不存在", HttpCode.INTERNAL_SERVER_ERROR.getCode());
        }

        // 删除用户会员信息
        deleteUserMembership(id);

        // 删除用户积分信息
        deleteUserPoints(id);

        // 删除用户本身
        int result = usersMapper.deleteById(id);
        if (result <= 0) {
            throw new ServiceException("删除用户失败", HttpCode.INTERNAL_SERVER_ERROR.getCode());
        }

        return true;
    }

    @Override
    public Boolean addUser(AddUserReq addUserReq) {
        return createNewUser(addUserReq);
    }

    /**
     * 删除用户会员信息
     * @param userId 用户ID
     */
    private void deleteUserMembership(Long userId) {
        // 查找用户当前会员信息
        UserMemberships userMembership = userMembershipsMapper.selectOne(new LambdaQueryWrapper<UserMemberships>()
                .eq(UserMemberships::getUserId, userId));

        // 如果存在会员信息，则删除
        if (userMembership != null) {
            userMembershipsMapper.deleteById(userMembership.getId());
        }
    }

    /**
     * 删除用户积分信息
     * @param userId 用户ID
     */
    private void deleteUserPoints(Long userId) {
        // 查找用户积分信息
        UserPoints userPoints = userPointsMapper.selectOne(new LambdaQueryWrapper<UserPoints>()
                .eq(UserPoints::getUserId, userId));

        // 如果存在积分信息，则删除
        if (userPoints != null) {
            userPointsMapper.deleteById(userPoints.getId());
        }
    }

    /**
     * 验证用户信息请求
     * @param userInfoReq 用户信息请求
     */
    private void validateUserInfoRequest(UserInfoReq userInfoReq) {
        if (userInfoReq.getId() == null) {
            throw new ServiceException("用户ID不能为空", HttpCode.INTERNAL_SERVER_ERROR.getCode());
        }

        // 检查用户是否存在
        Users existingUser = usersMapper.selectById(userInfoReq.getId());
        if (existingUser == null) {
            throw new ServiceException("用户不存在", HttpCode.INTERNAL_SERVER_ERROR.getCode());
        }

        // 如果修改了用户名，检查是否与其他用户冲突
        if (StringUtils.hasText(userInfoReq.getUsername()) && !userInfoReq.getUsername().equals(existingUser.getUsername())) {
            validateUsernameUniqueness(userInfoReq.getUsername(), userInfoReq.getId());
        }

        // 如果修改了邮箱，检查是否与其他用户冲突
        if (StringUtils.hasText(userInfoReq.getEmail()) && !userInfoReq.getEmail().equals(existingUser.getEmail())) {
            validateEmailUniqueness(userInfoReq.getEmail(), userInfoReq.getId());
        }
    }

    /**
     * 验证用户名唯一性
     * @param username 用户名
     * @param userId 用户ID
     */
    private void validateUsernameUniqueness(String username, Long userId) {
        Users usernameCheck = usersMapper.selectOne(new LambdaQueryWrapper<Users>()
                .eq(Users::getUsername, username)
                .ne(Users::getId, userId));

        if (usernameCheck != null) {
            throw new ServiceException("用户名已被使用", HttpCode.INTERNAL_SERVER_ERROR.getCode());
        }
    }

    /**
     * 验证邮箱唯一性
     * @param email 邮箱
     * @param userId 用户ID
     */
    private void validateEmailUniqueness(String email, Long userId) {
        Users emailCheck = usersMapper.selectOne(new LambdaQueryWrapper<Users>()
                .eq(Users::getEmail, email)
                .ne(Users::getId, userId));

        if (emailCheck != null) {
            throw new ServiceException("邮箱已被使用", HttpCode.INTERNAL_SERVER_ERROR.getCode());
        }
    }

    /**
     * 更新用户基本信息
     * @param userInfoReq 用户信息请求
     */
    private void updateBasicUserInfo(UserInfoReq userInfoReq) {
        Users userToUpdate = new Users();
        userToUpdate.setId(userInfoReq.getId());

        if (StringUtils.hasText(userInfoReq.getUsername())) {
            userToUpdate.setUsername(userInfoReq.getUsername());
        }

        if (StringUtils.hasText(userInfoReq.getEmail())) {
            userToUpdate.setEmail(userInfoReq.getEmail());
        }

        if (StringUtils.hasText(userInfoReq.getFullName())) {
            userToUpdate.setFullName(userInfoReq.getFullName());
        }

        if (StringUtils.hasText(userInfoReq.getPhone())) {
            userToUpdate.setPhone(userInfoReq.getPhone());
        }

        if (userInfoReq.getStatus() != null) {
            userToUpdate.setStatus(userInfoReq.getStatus());
        }

        if (StringUtils.hasText(userInfoReq.getRole())) {
            userToUpdate.setRole(userInfoReq.getRole());
        }

        // 如果有密码更新，进行加密
        if (StringUtils.hasText(userInfoReq.getPassword())) {
            userToUpdate.setPassword(MD5Utils.encrypt(userInfoReq.getPassword()));
        }

        userToUpdate.setUpdatedAt(new Date());

        // 更新用户信息
        int userUpdateResult = usersMapper.updateById(userToUpdate);
        if (userUpdateResult <= 0) {
            throw new ServiceException("更新用户信息失败", HttpCode.INTERNAL_SERVER_ERROR.getCode());
        }
    }

    /**
     * 更新用户会员信息
     * @param userInfoReq 用户信息请求
     */
    private void updateUserMembership(UserInfoReq userInfoReq) {
        // 查找用户当前会员信息
        UserMemberships userMembership = userMembershipsMapper.selectOne(new LambdaQueryWrapper<UserMemberships>()
                .eq(UserMemberships::getUserId, userInfoReq.getId()));

        // 如果membershipLevelId为0或null，且用户当前有会员信息，则表示取消会员
        if ((userInfoReq.getMembershipLevelId() == null || userInfoReq.getMembershipLevelId() == 0) && userMembership != null) {
            // 删除会员记录，表示取消会员
            userMembershipsMapper.deleteById(userMembership.getId());
        }
        // 如果提供了有效的会员等级ID，则更新或创建会员信息
        else if (userInfoReq.getMembershipLevelId() != null && userInfoReq.getMembershipLevelId() > 0) {
            updateOrCreateMembership(userInfoReq, userMembership);
        }
    }

    /**
     * 更新或创建会员信息
     * @param userInfoReq 用户信息请求
     * @param existingMembership 现有会员信息，可能为null
     */
    private void updateOrCreateMembership(UserInfoReq userInfoReq, UserMemberships existingMembership) {
        // 检查会员等级是否存在
        MembershipLevels membershipLevel = membershipLevelsMapper.selectById(userInfoReq.getMembershipLevelId());
        if (membershipLevel == null) {
            throw new ServiceException("会员等级不存在", HttpCode.INTERNAL_SERVER_ERROR.getCode());
        }

        Date expireTime = userInfoReq.getMembershipExpireTime();
        if (expireTime == null) {
            // 如果没有提供过期时间，默认设置为一年后
            expireTime = new Date(System.currentTimeMillis() + 365L * 24 * 60 * 60 * 1000);
        }

        if (existingMembership == null) {
            // 创建新的会员记录
            UserMemberships newMembership = new UserMemberships();
            newMembership.setUserId(userInfoReq.getId());
            newMembership.setLevelId(userInfoReq.getMembershipLevelId());
            newMembership.setExpiryDate(expireTime);
            newMembership.setCreatedAt(new Date());
            newMembership.setUpdatedAt(new Date());

            userMembershipsMapper.insert(newMembership);
        } else {
            // 更新现有会员记录
            UserMemberships membershipToUpdate = new UserMemberships();
            membershipToUpdate.setId(existingMembership.getId());
            membershipToUpdate.setLevelId(userInfoReq.getMembershipLevelId());
            membershipToUpdate.setExpiryDate(expireTime);
            membershipToUpdate.setUpdatedAt(new Date());

            userMembershipsMapper.updateById(membershipToUpdate);
        }
    }

    /**
     * 更新用户积分信息
     * @param userInfoReq 用户信息请求
     */
    private void updateUserPoints(UserInfoReq userInfoReq) {
        UserPoints userPoints = userPointsMapper.selectOne(new LambdaQueryWrapper<UserPoints>()
                .eq(UserPoints::getUserId, userInfoReq.getId()));

        if (userPoints == null) {
            throw new ServiceException("用户积分信息不存在", HttpCode.INTERNAL_SERVER_ERROR.getCode());
        }

        // 计算积分变化
        int currentBalance = userPoints.getBalance();
        int pointsDifference = userInfoReq.getPoints() - currentBalance;

        UserPoints pointsToUpdate = new UserPoints();
        pointsToUpdate.setId(userPoints.getId());
        pointsToUpdate.setBalance(userInfoReq.getPoints());

        // 更新总收入或总消费，取决于积分是增加还是减少
        if (pointsDifference > 0) {
            // 积分增加，更新总收入
            pointsToUpdate.setTotalEarned(userPoints.getTotalEarned() + pointsDifference);
        } else if (pointsDifference < 0) {
            // 积分减少，更新总消费
            pointsToUpdate.setTotalConsumed(userPoints.getTotalConsumed() - pointsDifference);
        }

        userPointsMapper.updateById(pointsToUpdate);
    }
}
