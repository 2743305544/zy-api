package aks.com.web.domain.common.req;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;


import java.util.Date;

/**
 * @author Shi Yi
 * @date 2025/5/26
 * @Description
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserReq {


    @NotNull(message = "用户名不能为空")
    @Length(min = 2, max = 20, message = "用户名长度在2-20之间")
    private String username;

    /**
     *
     */
    @NotNull(message = "密码不能为空")
    @Length(min = 6, max = 20, message = "密码长度在6-20之间")
    private String password;

    /**
     *
     */
    @NotNull(message = "邮箱不能为空")
    private String email;

    /**
     *
     */
    @NotNull(message = "姓名不能为空")
    @Length(min = 2, max = 20, message = "昵称长度在2-20之间")
    private String fullName;

    /**
     *
     */
    @NotNull(message = "手机号不能为空")
    private String phone;

    /**
     * 图形验证码唯一标识
     */
    @NotNull(message = "图形验证码不能为空")
    private String captchaKey;

    /**
     * 图形验证码值
     */
    @NotNull(message = "图形验证码不能为空")
    private String captchaCode;

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
