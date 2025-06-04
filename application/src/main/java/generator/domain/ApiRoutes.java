package generator.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName api_routes
 */
@TableName(value ="api_routes")
@Data
public class ApiRoutes {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 
     */
    private Long endpointId;

    /**
     * 
     */
    private Long targetId;

    /**
     * 目标路径，支持变量替换，如/api/{param}
     */
    private String targetPath;

    /**
     * 目标HTTP方法，如果为NULL则使用原始请求方法
     */
    private String targetMethod;

    /**
     * 权重，用于负载均衡
     */
    private Integer weight;

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