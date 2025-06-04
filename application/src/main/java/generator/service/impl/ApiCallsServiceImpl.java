package generator.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import generator.domain.ApiCalls;
import generator.service.ApiCallsService;
import generator.mapper.ApiCallsMapper;
import org.springframework.stereotype.Service;

/**
* @author 34011
* @description 针对表【api_calls】的数据库操作Service实现
* @createDate 2025-06-04 17:15:04
*/
@Service
public class ApiCallsServiceImpl extends ServiceImpl<ApiCallsMapper, ApiCalls>
    implements ApiCallsService{

}




