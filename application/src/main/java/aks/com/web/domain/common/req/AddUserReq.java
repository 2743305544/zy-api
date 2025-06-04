package aks.com.web.domain.common.req;

import aks.com.web.util.deserializer.StringToLongDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.validation.constraints.Email;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.util.Date;

/**
 * @author Shi Yi
 * @date 2025/6/4
 * @Description
 */
@Data
public class AddUserReq {


    /**
     *
     */
    @Length(min = 2, max = 20, message = "用户名长度在2-20之间")
    private String username;

    /**
     *
     */
    private String password;

    /**
     *
     */
    @Email(message = "邮箱格式不正确")
    private String email;

    /**
     *
     */
    private String fullName;

    /**
     *
     */
    @Length(min = 11, max = 11, message = "手机号长度为11位")
    private String phone;


    /**
     * admin, user
     */

    private String role;

}
