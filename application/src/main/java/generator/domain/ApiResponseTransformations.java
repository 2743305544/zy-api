package generator.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName api_response_transformations
 */
@TableName(value ="api_response_transformations")
@Data
public class ApiResponseTransformations {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * Reference to api_route_mappings
     */
    private Long routeMappingId;

    /**
     * header, status_code, body, template
     */
    private String transformationType;

    /**
     * Source parameter name
     */
    private String sourceParameter;

    /**
     * Target parameter name
     */
    private String targetParameter;

    /**
     * Rule or template for transformation
     */
    private String transformationRule;

    /**
     * Order of execution
     */
    private Integer executionOrder;

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