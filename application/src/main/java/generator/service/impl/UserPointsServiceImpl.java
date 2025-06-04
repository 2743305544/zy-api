package generator.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import generator.domain.UserPoints;
import generator.service.UserPointsService;
import generator.mapper.UserPointsMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

/**
* @author 34011
* @description 针对表【user_points】的数据库操作Service实现
* @createDate 2025-06-04 17:15:03
*/
@Service
public class UserPointsServiceImpl extends ServiceImpl<UserPointsMapper, UserPoints>
    implements UserPointsService{

    @Resource
    private UserPointsMapper userPointsMapper;



    @Override
    public boolean updateUserPoints(Long userId, Integer points) {
        UserPoints userPoints = userPointsMapper.selectById(userId);
        if (userPoints == null) {
            return false;
        }
        userPoints.setBalance(userPoints.getBalance() + points);
        return userPointsMapper.updateById(userPoints) > 0;
    }

}




