package aks.com.web.domain.common.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import generator.domain.MembershipLevels;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author Shi Yi
 * @date 2025/6/3
 * @Description
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserInfoVo {
    /**
     *
     */
    private Long id;

    /**
     *
     */
    private String username;

    /**
     *
     */
    private String password;

    /**
     *
     */
    private String email;

    /**
     *
     */
    private String fullName;

    /**
     *
     */
    private String phone;

    /**
     * 0-disabled, 1-enabled
     */
    private Integer status;

    /**
     * admin, user
     */
    private String role;

    /**
     * 0-not deleted, 1-deleted
     */
    private Integer isDeleted;

    /**
     *
     */
    private Date createdAt;

    /**
     *
     */
    private Date updatedAt;


    private Integer points;

    private  Integer totalEarned;

    private Integer totalConsumed;

    private Long membershipLevelId;

    private Date membershipExpireTime;
}
