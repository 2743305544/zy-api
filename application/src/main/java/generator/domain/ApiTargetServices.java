package generator.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName api_target_services
 */
@TableName(value ="api_target_services")
@Data
public class ApiTargetServices {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 
     */
    private String name;

    /**
     * 
     */
    private String description;

    /**
     * Base URL of the target service
     */
    private String baseUrl;

    /**
     * Path for health check
     */
    private String healthCheckPath;

    /**
     * Timeout in milliseconds
     */
    private Integer timeoutMs;

    /**
     * 0-inactive, 1-active
     */
    private Integer isActive;

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