package generator.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName api_request_transforms
 */
@TableName(value ="api_request_transforms")
@Data
public class ApiRequestTransforms {
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
     * header, query, path, body
     */
    private String transformType;

    /**
     * 源参数名
     */
    private String sourceParam;

    /**
     * 目标参数名
     */
    private String targetParam;

    /**
     * 转换规则，如模板、正则表达式等
     */
    private String transformRule;

    /**
     * 0-optional, 1-required
     */
    private Integer isRequired;

    /**
     * 默认值
     */
    private String defaultValue;

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