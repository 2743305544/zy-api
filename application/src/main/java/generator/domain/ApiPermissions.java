package generator.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName api_permissions
 */
@TableName(value ="api_permissions")
@Data
public class ApiPermissions {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 
     */
    private Long apiKeyId;

    /**
     * 
     */
    private Long apiId;

    /**
     * Requests per minute
     */
    private Integer rateLimit;

    /**
     * 0-disabled, 1-enabled
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