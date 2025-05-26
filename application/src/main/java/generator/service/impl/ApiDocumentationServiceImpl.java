package generator.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import generator.domain.ApiDocumentation;
import generator.service.ApiDocumentationService;
import generator.mapper.ApiDocumentationMapper;
import org.springframework.stereotype.Service;

/**
* @author 34011
* @description 针对表【api_documentation】的数据库操作Service实现
* @createDate 2025-05-26 13:23:18
*/
@Service
public class ApiDocumentationServiceImpl extends ServiceImpl<ApiDocumentationMapper, ApiDocumentation>
    implements ApiDocumentationService{

}




