package generator.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import generator.domain.ApiBilling;
import generator.service.ApiBillingService;
import generator.mapper.ApiBillingMapper;
import org.springframework.stereotype.Service;

/**
* @author 34011
* @description 针对表【api_billing】的数据库操作Service实现
* @createDate 2025-05-26 13:23:19
*/
@Service
public class ApiBillingServiceImpl extends ServiceImpl<ApiBillingMapper, ApiBilling>
    implements ApiBillingService{

}




