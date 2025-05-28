package aks.com.web.controller;

import aks.com.sdk.resp.RespEntity;
import aks.com.web.domain.common.req.ResetPasswordReq;
import aks.com.web.domain.common.req.UpdateEmailReq;
import aks.com.web.domain.common.req.UserUpdateReq;
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
 * @date 2025/5/27
 * @Description
 */

@RestController
@RequestMapping("/userInfo")
@Tag(name = "用户接口", description = "用户查看，修改接口")
public class UserController {

    @Resource
    private UsersService usersService;


    @GetMapping("/get")
    public RespEntity<UserVo> get() {
        StpUtil.checkLogin();
        return RespEntity.success(usersService.getUser( (Long) StpUtil.getLoginId()));
    }

    @PutMapping("/update")
    public RespEntity<Boolean> update(@RequestBody @Valid UserUpdateReq userUpdateReq) {
        StpUtil.checkLogin();
        return RespEntity.success(usersService.update(userUpdateReq));
    }
    
    @PutMapping("/updateEmail")
    @Operation(summary = "修改邮箱", description = "修改用户邮箱，需要邮箱验证码")
    public RespEntity<Boolean> updateEmail(@RequestBody @Valid UpdateEmailReq updateEmailReq) {
        StpUtil.checkLogin();
        return RespEntity.success(usersService.updateEmail(updateEmailReq, (Long) StpUtil.getLoginId()));
    }
    
    @PostMapping("/resetPassword")
    @Operation(summary = "重置密码", description = "通过邮箱验证码重置密码")
    public RespEntity<Boolean> resetPassword(@RequestBody @Valid ResetPasswordReq resetPasswordReq) {
        return RespEntity.success(usersService.resetPassword(resetPasswordReq));
    }
}
