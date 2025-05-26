package generator.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import generator.domain.ApiPermissions;
import generator.service.ApiPermissionsService;
import generator.mapper.ApiPermissionsMapper;
import org.springframework.stereotype.Service;

/**
* @author 34011
* @description 针对表【api_permissions】的数据库操作Service实现
* @createDate 2025-05-26 13:23:18
*/
@Service
public class ApiPermissionsServiceImpl extends ServiceImpl<ApiPermissionsMapper, ApiPermissions>
    implements ApiPermissionsService{

}




