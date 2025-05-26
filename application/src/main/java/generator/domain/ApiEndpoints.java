package generator.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName api_endpoints
 */
@TableName(value ="api_endpoints")
@Data
public class ApiEndpoints {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 
     */
    private Long apiId;

    /**
     * 
     */
    private String path;

    /**
     * GET, POST, PUT, DELETE, etc.
     */
    private String method;

    /**
     * 
     */
    private String summary;

    /**
     * 
     */
    private String description;

    /**
     * 
     */
    private Object requestSchema;

    /**
     * 
     */
    private Object responseSchema;

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