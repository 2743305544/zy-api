package generator.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import generator.domain.ApiAuthConfigs;
import generator.service.ApiAuthConfigsService;
import generator.mapper.ApiAuthConfigsMapper;
import org.springframework.stereotype.Service;

/**
* @author 34011
* @description 针对表【api_auth_configs】的数据库操作Service实现
* @createDate 2025-06-04 17:15:04
*/
@Service
public class ApiAuthConfigsServiceImpl extends ServiceImpl<ApiAuthConfigsMapper, ApiAuthConfigs>
    implements ApiAuthConfigsService{

}




