package generator.service.impl;

import aks.com.sdk.exception.ServiceException;
import aks.com.sdk.resp.HttpCode;
import aks.com.sdk.util.md5.MD5Utils;
import aks.com.web.domain.common.req.UserReq;
import aks.com.web.domain.common.vo.UserVo;
import aks.com.web.enums.StatusEnum;
import aks.com.web.util.CaptchaUtils;
import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import generator.domain.Users;
import generator.service.UsersService;
import generator.mapper.UsersMapper;
import jakarta.annotation.Resource;
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
        // 验证码认证
        if (!StringUtils.hasText(userReq.getCaptchaKey()) || !StringUtils.hasText(userReq.getCaptchaCode())) {
            throw new ServiceException(HttpCode.CAPTCHA_ERROR);
        }

        // 验证验证码
        captchaUtils.validateCaptchaWithException(userReq.getCaptchaKey(), userReq.getCaptchaCode());

        // 检查用户名是否已存在
        Users existingUser = usersMapper.selectOne(new LambdaQueryWrapper<Users>()
                .eq(Users::getUsername, userReq.getUsername())
        );

        if (existingUser != null) {
            throw new ServiceException("用户名已存在", HttpCode.INTERNAL_SERVER_ERROR.getCode());
        }

        // 密码加密
        userReq.setPassword(MD5Utils.encrypt(userReq.getPassword()));

        return createNewUser(userReq);
    }



    private boolean createNewUser(UserReq userReq) {
        // 创建新用户
        Users user = new Users();
        user.setUsername(userReq.getUsername());
        user.setPassword(userReq.getPassword());
        user.setEmail(userReq.getEmail());
        user.setFullName(userReq.getFullName());
        user.setPhone(userReq.getPhone());
        user.setRole("user");
        user.setStatus(1);
        user.setIsDeleted(0);
        user.setCreatedAt(new java.util.Date());
        user.setUpdatedAt(new java.util.Date());

        // 保存用户
        int result = usersMapper.insert(user);

        return result > 0;
    }
}
