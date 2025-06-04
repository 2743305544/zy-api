package generator.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import generator.domain.MembershipLevels;
import generator.domain.UserMemberships;
import generator.mapper.UserMembershipsMapper;
import generator.mapper.UsersMapper;
import generator.service.MembershipLevelsService;
import generator.mapper.MembershipLevelsMapper;
import generator.service.UsersService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* @author 34011
* @description 针对表【membership_levels】的数据库操作Service实现
* @createDate 2025-06-03 19:29:57
*/
@Service
public class MembershipLevelsServiceImpl extends ServiceImpl<MembershipLevelsMapper, MembershipLevels>
    implements MembershipLevelsService{

    @Resource
    private MembershipLevelsMapper membershipLevelsMapper;

    @Resource
    private UserMembershipsMapper userMembershipsMapper;


    @Override
    @Transactional
    public Boolean removeMembershipLevel(String id) {
        membershipLevelsMapper.deleteById(id);
        userMembershipsMapper.delete(new LambdaQueryWrapper<UserMemberships>()
                .eq(UserMemberships::getLevelId, id));
        return true;
    }

    @Override
    public Boolean enableMembershipLevel(String id, Boolean enable) {
        var update = membershipLevelsMapper.selectById(id).setIsActive(enable ? 1 : 0);
        membershipLevelsMapper.updateById(update);
        return true;
    }
}




