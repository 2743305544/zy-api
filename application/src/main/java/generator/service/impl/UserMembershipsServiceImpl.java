package generator.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import generator.domain.UserMemberships;
import generator.service.UserMembershipsService;
import generator.mapper.UserMembershipsMapper;
import org.springframework.stereotype.Service;

/**
* @author 34011
* @description 针对表【user_memberships】的数据库操作Service实现
* @createDate 2025-06-03 19:29:56
*/
@Service
public class UserMembershipsServiceImpl extends ServiceImpl<UserMembershipsMapper, UserMemberships>
    implements UserMembershipsService{

}




