package generator.service;

import generator.domain.UserPoints;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author 34011
* @description 针对表【user_points】的数据库操作Service
* @createDate 2025-06-04 17:15:03
*/
public interface UserPointsService extends IService<UserPoints> {

    boolean updateUserPoints(Long userId, Integer points);
}
