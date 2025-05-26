package aks.com.web.controller;

import aks.com.sdk.resp.RespEntity;
import aks.com.web.domain.common.req.UserReq;
import aks.com.web.domain.common.vo.UserVo;
import cn.dev33.satoken.stp.StpUtil;
import generator.service.UsersService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

/**
 * @author Shi Yi
 * @date 2025/5/26
 * @Descriptzion 登录
 */
@RestController
@RequestMapping("/user")
public class LoginController {

    @Resource
    private UsersService usersService;


    @PostMapping("/login")
    public RespEntity<UserVo> login(@RequestParam(value = "username") String username,
                                    @RequestParam(value = "password") String password) {
        return RespEntity.success(usersService.login(username, password));
    }

    @GetMapping("/logout")
    public RespEntity<Boolean> logout() {
        StpUtil.checkLogin();
        StpUtil.logout();
        return RespEntity.success(Boolean.TRUE);
    }

    @PostMapping("/register")
    public RespEntity<Boolean> register(@RequestBody UserReq userReq) {
        return RespEntity.success(usersService.register(userReq));
    }
}
