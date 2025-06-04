package generator.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import generator.domain.ApiTargets;
import generator.service.ApiTargetsService;
import generator.mapper.ApiTargetsMapper;
import org.springframework.stereotype.Service;

/**
* @author 34011
* @description 针对表【api_targets】的数据库操作Service实现
* @createDate 2025-06-04 17:15:04
*/
@Service
public class ApiTargetsServiceImpl extends ServiceImpl<ApiTargetsMapper, ApiTargets>
    implements ApiTargetsService{

}




