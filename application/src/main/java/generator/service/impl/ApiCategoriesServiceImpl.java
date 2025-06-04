package generator.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import generator.domain.ApiCategories;
import generator.service.ApiCategoriesService;
import generator.mapper.ApiCategoriesMapper;
import org.springframework.stereotype.Service;

/**
* @author 34011
* @description 针对表【api_categories】的数据库操作Service实现
* @createDate 2025-06-04 17:15:04
*/
@Service
public class ApiCategoriesServiceImpl extends ServiceImpl<ApiCategoriesMapper, ApiCategories>
    implements ApiCategoriesService{

}




