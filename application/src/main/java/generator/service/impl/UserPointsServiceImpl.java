package generator.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import generator.domain.UserPoints;
import generator.service.UserPointsService;
import generator.mapper.UserPointsMapper;
import org.springframework.stereotype.Service;

/**
* @author 34011
* @description 针对表【user_points】的数据库操作Service实现
* @createDate 2025-06-03 19:29:56
*/
@Service
public class UserPointsServiceImpl extends ServiceImpl<UserPointsMapper, UserPoints>
    implements UserPointsService{

}




