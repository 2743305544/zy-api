package generator.service;

import aks.com.web.domain.common.req.*;
import aks.com.web.domain.common.vo.UserInfoVo;
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

    UserVo getUser(Long loginId);

    Boolean update(UserUpdateReq userUpdateReq);

    Boolean updateEmail(UpdateEmailReq updateEmailReq, Long userId);

    Boolean resetPassword(ResetPasswordReq resetPasswordReq);

    UserInfoVo getUserInfoById(Long id);

    /**
     * Update user information by admin
     *
     * @param userInfoReq User information request
     * @return true if update successful, false otherwise
     */
    Boolean updateUserInfo(UserInfoReq userInfoReq);

    /**
     * Delete user by ID
     *
     * @param id User ID to delete
     * @return true if deletion successful, false otherwise
     */
    Boolean deleteUserInfo(Long id);

    Boolean addUser(AddUserReq addUserReq);
}
