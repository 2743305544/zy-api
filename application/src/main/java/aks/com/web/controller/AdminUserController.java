package aks.com.web.controller;

import aks.com.sdk.resp.RespEntity;
import aks.com.web.domain.common.req.AddUserReq;
import aks.com.web.domain.common.req.UserInfoReq;
import aks.com.web.domain.common.vo.UserInfoVo;
import cn.dev33.satoken.annotation.SaCheckPermission;
import generator.domain.Users;
import generator.service.UsersService;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

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
    public RespEntity<UserInfoVo> info(@RequestParam("id") Long id) {
        return RespEntity.success(usersService.getUserInfoById(id));
    }

    @PutMapping("/update")
    @SaCheckPermission("admin")
    public RespEntity<Boolean> update(@Valid @RequestBody UserInfoReq userInfoReq) {

        return RespEntity.success(usersService.updateUserInfo((userInfoReq)));
    }

    @DeleteMapping("/delete")
    @SaCheckPermission("admin")
    public RespEntity<Boolean> delete(@RequestParam("id") Long id) {
        return RespEntity.success(usersService.deleteUserInfo(id));
    }

    @PostMapping("/add")
    @SaCheckPermission("admin")
    public RespEntity<Boolean> add(@Valid @RequestBody AddUserReq addUserReq) {
        return RespEntity.success(usersService.addUser(addUserReq));
    }


}
