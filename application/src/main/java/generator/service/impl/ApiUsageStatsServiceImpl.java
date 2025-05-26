package generator.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import generator.domain.ApiUsageStats;
import generator.service.ApiUsageStatsService;
import generator.mapper.ApiUsageStatsMapper;
import org.springframework.stereotype.Service;

/**
* @author 34011
* @description 针对表【api_usage_stats】的数据库操作Service实现
* @createDate 2025-05-26 13:23:18
*/
@Service
public class ApiUsageStatsServiceImpl extends ServiceImpl<ApiUsageStatsMapper, ApiUsageStats>
    implements ApiUsageStatsService{

}




