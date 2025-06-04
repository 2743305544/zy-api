package generator.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName api_response_transforms
 */
@TableName(value ="api_response_transforms")
@Data
public class ApiResponseTransforms {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 
     */
    private Long routeId;

    /**
     * 匹配的HTTP状态码，NULL表示匹配所有
     */
    private Integer statusCode;

    /**
     * 响应转换规则，可以是JSON模板、JSONPATH表达式等
     */
    private String transformRule;

    /**
     * 错误处理策略：pass_through, custom_response
     */
    private String errorHandling;

    /**
     * 自定义错误响应
     */
    private String errorResponse;

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