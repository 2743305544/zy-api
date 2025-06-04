package aks.com.web.service;

import cn.dev33.satoken.stp.StpInterface;
import cn.dev33.satoken.stp.StpUtil;
import generator.domain.Users;
import generator.service.UsersService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 34011
 */

@Component    // 保证此类被 SpringBoot 扫描，完成 Sa-Token 的自定义权限验证扩展
public class StpInterfaceImpl implements StpInterface {

    @Resource
    private UsersService usersService;

    /**
     * 返回一个账号所拥有的权限码集合
     */
    @Override
    public List<String> getPermissionList(Object loginId, String loginType) {
        StpUtil.checkLogin();
        Users user = usersService.getById((Long)loginId);
        List<String> permissionList = new ArrayList<>();
        permissionList.add(user.getRole());
        return permissionList;
    }

    @Override
    public List<String> getRoleList(Object o, String s) {
        return List.of();
    }


}
