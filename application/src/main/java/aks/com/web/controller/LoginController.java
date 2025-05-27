package aks.com.web.controller;

import aks.com.sdk.exception.ServiceException;
import aks.com.sdk.resp.HttpCode;
import aks.com.sdk.resp.RespEntity;
import aks.com.web.domain.common.req.UserReq;
import aks.com.web.domain.common.vo.UserVo;
import cn.dev33.satoken.stp.StpUtil;
import generator.service.UsersService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

/**
 * @author Shi Yi
 * @date 2025/5/26
 * @Description 登录
 */

@RestController
@RequestMapping("/user")
@Tag(name = "用户接口", description = "用户登录、注册相关接口")
public class LoginController {

    @Resource
    private UsersService usersService;

    @PostMapping("/login")
    @Operation(summary = "用户登录", description = "用户名密码登录")
    public RespEntity<UserVo> login(@RequestParam(value = "username") String username,
                                    @RequestParam(value = "password") String password) {
        return RespEntity.success(usersService.login(username, password));
    }

    @GetMapping("/logout")
    @Operation(summary = "用户登出", description = "退出登录")
    public RespEntity<Boolean> logout() {
        StpUtil.checkLogin();
        StpUtil.logout();
        return RespEntity.success(Boolean.TRUE);
    }

    @PostMapping("/register")
    @Operation(summary = "用户注册", description = "注册新用户，需要图形验证码和邮箱验证码")
    public RespEntity<Boolean> register(@Valid @RequestBody UserReq userReq) {
        return RespEntity.success(usersService.register(userReq));
    }
}
