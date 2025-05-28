package aks.com.web.domain.common.req;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Shi Yi
 * @date 2025/5/28
 * @Description 用户信息更新请求
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserUpdateReq {
    
    /**
     * 用户姓名
     */
    @NotNull(message = "姓名不能为空")
    @Size(min = 2, max = 20, message = "姓名长度在2-20之间")
    private String fullName;
    
    /**
     * 手机号
     */
    private String phone;
}
