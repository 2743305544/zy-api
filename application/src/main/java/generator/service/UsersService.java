package generator.service;

import aks.com.web.domain.common.req.UserReq;
import aks.com.web.domain.common.vo.UserVo;
import generator.domain.Users;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author 34011
* @description 针对表【users】的数据库操作Service
* @createDate 2025-05-26 13:23:18
*/
public interface UsersService extends IService<Users> {

    UserVo login(String username, String password);

    Boolean register(UserReq userReq);
}
