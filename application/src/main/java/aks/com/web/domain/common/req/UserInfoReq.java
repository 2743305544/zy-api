package aks.com.web.domain.common.req;

import aks.com.web.util.deserializer.StringToLongDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.util.Date;

/**
 * @author Shi Yi
 * @date 2025/6/4
 * @Description User information request object
 */
@Data
public class UserInfoReq {
    /**
     * User ID
     */
    @JsonDeserialize(using = StringToLongDeserializer.class)
    private Long id;

    /**
     * Username
     */
    @Length(min = 2, max = 20, message = "用户名长度在2-20之间")
    private String username;

    /**
     * Email address
     */
    @Email
    private String email;

    /**
     * Phone number
     */
    @Length(min = 11, max = 11, message = "手机号长度为11位")
    private String phone;

    /**
     * User status (0: disabled, 1: enabled)
     */
    private Integer status;

    /**
     * Membership level ID
     */
    @JsonDeserialize(using = StringToLongDeserializer.class)
    private Long membershipLevelId;

    /**
     *
     */
    @Length(min = 6, max = 20, message = "密码长度在6-20之间")
    private String password;

    /**
     *
     */
    @Length(min = 2, max = 20, message = "昵称长度在2-20之间")
    private String fullName;

    /**
     * admin, user
     */
    @Pattern(regexp = "admin|user|developer", message = "角色必须是 admin、user 或 developer")
    private String role;



    private Integer points;


    private Date membershipExpireTime;
}
