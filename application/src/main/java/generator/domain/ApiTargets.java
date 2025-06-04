package generator.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName api_targets
 */
@TableName(value ="api_targets")
@Data
public class ApiTargets {
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
     * 目标服务名称
     */
    private String name;

    /**
     * 目标服务基础URL
     */
    private String baseUrl;

    /**
     * 
     */
    private String description;

    /**
     * 请求超时时间（毫秒）
     */
    private Integer timeout;

    /**
     * 失败重试次数
     */
    private Integer retryCount;

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