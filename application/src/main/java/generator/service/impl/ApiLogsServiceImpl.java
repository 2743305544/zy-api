package generator.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import generator.domain.ApiLogs;
import generator.service.ApiLogsService;
import generator.mapper.ApiLogsMapper;
import org.springframework.stereotype.Service;

/**
* @author 34011
* @description 针对表【api_logs】的数据库操作Service实现
* @createDate 2025-05-26 13:23:18
*/
@Service
public class ApiLogsServiceImpl extends ServiceImpl<ApiLogsMapper, ApiLogs>
    implements ApiLogsService{

}




