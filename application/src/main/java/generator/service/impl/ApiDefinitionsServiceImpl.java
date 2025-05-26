package generator.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import generator.domain.ApiDefinitions;
import generator.service.ApiDefinitionsService;
import generator.mapper.ApiDefinitionsMapper;
import org.springframework.stereotype.Service;

/**
* @author 34011
* @description 针对表【api_definitions】的数据库操作Service实现
* @createDate 2025-05-26 13:23:19
*/
@Service
public class ApiDefinitionsServiceImpl extends ServiceImpl<ApiDefinitionsMapper, ApiDefinitions>
    implements ApiDefinitionsService{

}




