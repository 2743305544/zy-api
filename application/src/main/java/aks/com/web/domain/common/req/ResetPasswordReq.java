package aks.com.web.domain.common.req;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Shi Yi
 * @date 2025/5/28
 * @Description 重置密码请求参数
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResetPasswordReq {

    /**
     * 邮箱地址
     */
    @NotNull(message = "邮箱不能为空")
    @Email(message = "邮箱格式不正确")
    private String email;

    /**
     * 新密码
     */
    @NotNull(message = "密码不能为空")
    @Size(min = 6, max = 20, message = "密码长度在6-20之间")
    private String newPassword;

    /**
     * 邮箱验证码唯一标识
     */
    @NotNull(message = "邮箱验证码不能为空")
    private String emailCaptchaKey;

    /**
     * 邮箱验证码值
     */
    @NotNull(message = "邮箱验证码不能为空")
    private String emailCaptchaCode;
}
