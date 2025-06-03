package generator.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import generator.domain.SystemLogs;
import generator.service.SystemLogsService;
import generator.mapper.SystemLogsMapper;
import org.springframework.stereotype.Service;

/**
* @author 34011
* @description 针对表【system_logs】的数据库操作Service实现
* @createDate 2025-06-03 19:29:56
*/
@Service
public class SystemLogsServiceImpl extends ServiceImpl<SystemLogsMapper, SystemLogs>
    implements SystemLogsService{

}




