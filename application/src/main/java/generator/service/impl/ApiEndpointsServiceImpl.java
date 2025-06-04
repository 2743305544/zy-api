package generator.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import generator.domain.ApiEndpoints;
import generator.service.ApiEndpointsService;
import generator.mapper.ApiEndpointsMapper;
import org.springframework.stereotype.Service;

/**
* @author 34011
* @description 针对表【api_endpoints】的数据库操作Service实现
* @createDate 2025-06-04 17:15:04
*/
@Service
public class ApiEndpointsServiceImpl extends ServiceImpl<ApiEndpointsMapper, ApiEndpoints>
    implements ApiEndpointsService{

}




