package generator.service.impl;

import aks.com.sdk.exception.ServiceException;
import aks.com.sdk.resp.HttpCode;
import aks.com.sdk.util.md5.MD5Utils;
import aks.com.web.domain.common.req.UserReq;
import aks.com.web.domain.common.req.UserUpdateReq;
import aks.com.web.domain.common.req.UpdateEmailReq;
import aks.com.web.domain.common.req.ResetPasswordReq;
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
import org.springframework.util.StringUtils;

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
                .isDeleted(user.getIsDeleted())
                .createdAt(user.getCreatedAt())
                .updatedAt(user.getUpdatedAt())
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
}
