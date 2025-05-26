package generator.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName api_route_mappings
 */
@TableName(value ="api_route_mappings")
@Data
public class ApiRouteMappings {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * Reference to api_definitions
     */
    private Long apiId;

    /**
     * Reference to api_endpoints
     */
    private Long endpointId;

    /**
     * Reference to api_target_services
     */
    private Long targetServiceId;

    /**
     * Path on the target service
     */
    private String targetPath;

    /**
     * GET, POST, PUT, DELETE, etc.
     */
    private String requestMethod;

    /**
     * If different from request_method
     */
    private String targetRequestMethod;

    /**
     * Override default timeout
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