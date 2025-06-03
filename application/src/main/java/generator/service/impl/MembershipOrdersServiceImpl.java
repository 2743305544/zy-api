package generator.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import generator.domain.MembershipOrders;
import generator.service.MembershipOrdersService;
import generator.mapper.MembershipOrdersMapper;
import org.springframework.stereotype.Service;

/**
* @author 34011
* @description 针对表【membership_orders】的数据库操作Service实现
* @createDate 2025-06-03 19:29:56
*/
@Service
public class MembershipOrdersServiceImpl extends ServiceImpl<MembershipOrdersMapper, MembershipOrders>
    implements MembershipOrdersService{

}




