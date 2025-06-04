package generator.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import generator.domain.PointTransactions;
import generator.service.PointTransactionsService;
import generator.mapper.PointTransactionsMapper;
import org.springframework.stereotype.Service;

/**
* @author 34011
* @description 针对表【point_transactions】的数据库操作Service实现
* @createDate 2025-06-04 17:15:04
*/
@Service
public class PointTransactionsServiceImpl extends ServiceImpl<PointTransactionsMapper, PointTransactions>
    implements PointTransactionsService{

}




