package generator.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import generator.domain.ApiKeys;
import generator.service.ApiKeysService;
import generator.mapper.ApiKeysMapper;
import org.springframework.stereotype.Service;

/**
* @author 34011
* @description 针对表【api_keys】的数据库操作Service实现
* @createDate 2025-06-03 19:29:57
*/
@Service
public class ApiKeysServiceImpl extends ServiceImpl<ApiKeysMapper, ApiKeys>
    implements ApiKeysService{

}




