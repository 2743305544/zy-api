package generator.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName api_definitions
 */
@TableName(value ="api_definitions")
@Data
public class ApiDefinitions {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 
     */
    private Long projectId;

    /**
     * 
     */
    private String name;

    /**
     * 
     */
    private String version;

    /**
     * 
     */
    private String description;

    /**
     * 
     */
    private String basePath;

    /**
     * OpenAPI/Swagger JSON definition
     */
    private String swaggerJson;

    /**
     * 0-draft, 1-published, 2-deprecated
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