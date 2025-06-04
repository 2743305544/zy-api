package generator.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import generator.domain.ApiRoutes;
import generator.service.ApiRoutesService;
import generator.mapper.ApiRoutesMapper;
import org.springframework.stereotype.Service;

/**
* @author 34011
* @description 针对表【api_routes】的数据库操作Service实现
* @createDate 2025-06-04 17:15:04
*/
@Service
public class ApiRoutesServiceImpl extends ServiceImpl<ApiRoutesMapper, ApiRoutes>
    implements ApiRoutesService{

}




