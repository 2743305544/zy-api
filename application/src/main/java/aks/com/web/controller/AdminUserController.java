package aks.com.web.controller;

import aks.com.sdk.resp.RespEntity;
import aks.com.web.domain.common.vo.UserInfoVo;
import cn.dev33.satoken.annotation.SaCheckPermission;
import generator.domain.Users;
import generator.service.UsersService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Shi Yi
 * @date 2025/6/3
 * @Description
 */
@RestController
@RequestMapping("/admin/user")
public class AdminUserController {

    @Resource
    private UsersService usersService;

    @GetMapping("/list")
    @SaCheckPermission("admin")
    public RespEntity<List<Users>> list() {
        return RespEntity.success(usersService.list());
    }

    @GetMapping("/info")
    @SaCheckPermission("admin")
    public RespEntity<UserInfoVo> info(Long id) {
        return RespEntity.success(usersService.getUserInfoById(id));
    }

}
