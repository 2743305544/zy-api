package generator.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import generator.domain.ApiProjects;
import generator.service.ApiProjectsService;
import generator.mapper.ApiProjectsMapper;
import org.springframework.stereotype.Service;

/**
* @author 34011
* @description 针对表【api_projects】的数据库操作Service实现
* @createDate 2025-05-26 13:23:18
*/
@Service
public class ApiProjectsServiceImpl extends ServiceImpl<ApiProjectsMapper, ApiProjects>
    implements ApiProjectsService{

}




