package aks.com.web.controller;

import aks.com.sdk.resp.RespEntity;
import aks.com.web.domain.common.req.AddMembershipLevelReq;
import aks.com.web.domain.common.vo.MembershipLevelsVo;
import cn.dev33.satoken.annotation.SaCheckPermission;
import generator.service.MembershipLevelsService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * @author Shi Yi
 * @date 2025/6/4
 * @Description
 */
@RestController
@RequestMapping("/admin/membership")
public class AdminMembershipController {


    @Resource
    private MembershipLevelsService membershipLevelsService;


    @GetMapping("/{id}")
    @SaCheckPermission("admin")
    public RespEntity<String> get(@PathVariable("id") String id) {
        return RespEntity.success(membershipLevelsService.getById(id).getName());
    }


    @GetMapping("/list")
    @SaCheckPermission("admin")
    public RespEntity<List<MembershipLevelsVo>> list() {
        return RespEntity.success(membershipLevelsService.list().stream().map(MembershipLevelsVo::new).toList());
    }

    @GetMapping("/info")
    @SaCheckPermission("admin")
    public RespEntity<MembershipLevelsVo> info(@RequestParam("id") String id) {
        return RespEntity.success(membershipLevelsService.getById(id).toVo());
    }

    @PutMapping("/update")
    @SaCheckPermission("admin")
    public RespEntity<String> update(@RequestBody MembershipLevelsVo membershipLevelsVo) {
        membershipLevelsService.updateById(membershipLevelsVo.toEntity());
        return RespEntity.success();
    }

    @PostMapping("/create")
    @SaCheckPermission("admin")
    public RespEntity<String> create(@RequestBody AddMembershipLevelReq addMembershipLevelReq) {
        membershipLevelsService.save(addMembershipLevelReq.toEntity());
        return RespEntity.success();
    }

    @DeleteMapping("/delete")
    @SaCheckPermission("admin")
    public RespEntity<Boolean> delete(@RequestParam("id") String id) {
        return RespEntity.success(membershipLevelsService.removeMembershipLevel(id));
    }

    @PostMapping("/enable")
    @SaCheckPermission("admin")
    public RespEntity<Boolean> enable(@RequestParam("id") String id , @RequestParam("enable") Boolean enable) {
        return RespEntity.success(membershipLevelsService.enableMembershipLevel(id, enable));
    }
}
