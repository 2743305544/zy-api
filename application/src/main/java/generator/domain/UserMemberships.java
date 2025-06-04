package generator.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName user_memberships
 */
@TableName(value ="user_memberships")
@Data
public class UserMemberships {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 
     */
    private Long userId;

    /**
     * 
     */
    private Long levelId;

    /**
     * 
     */
    private Date startDate;

    /**
     * 会员到期时间，NULL表示永久有效
     */
    private Date expiryDate;

    /**
     * 0-不自动续费, 1-自动续费
     */
    private Integer autoRenew;

    /**
     * 0-inactive, 1-active, 2-expired
     */
    private Integer status;

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
}