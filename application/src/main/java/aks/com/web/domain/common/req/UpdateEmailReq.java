package aks.com.web.domain.common.req;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Shi Yi
 * @date 2025/5/28
 * @Description 修改邮箱请求参数
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateEmailReq {

    /**
     * 新邮箱地址
     */
    @NotNull(message = "邮箱不能为空")
    @Email(message = "邮箱格式不正确")
    private String email;

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
