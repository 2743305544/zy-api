package generator.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import generator.domain.ApiCircuitBreakers;
import generator.service.ApiCircuitBreakersService;
import generator.mapper.ApiCircuitBreakersMapper;
import org.springframework.stereotype.Service;

/**
* @author 34011
* @description 针对表【api_circuit_breakers】的数据库操作Service实现
* @createDate 2025-05-26 13:23:19
*/
@Service
public class ApiCircuitBreakersServiceImpl extends ServiceImpl<ApiCircuitBreakersMapper, ApiCircuitBreakers>
    implements ApiCircuitBreakersService{

}




