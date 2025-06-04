package aks.com.web.domain.common.req;

import aks.com.web.util.deserializer.StringToLongDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;

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
    private String username;

    /**
     * Email address
     */
    private String email;

    /**
     * Phone number
     */
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
    private String password;

    /**
     *
     */
    private String fullName;

    /**
     * admin, user
     */
    private String role;



    private Integer points;

    private  Integer totalEarned;

    private Integer totalConsumed;

    private Date membershipExpireTime;
}
