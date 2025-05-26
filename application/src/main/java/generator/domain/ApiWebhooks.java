package generator.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName api_webhooks
 */
@TableName(value ="api_webhooks")
@Data
public class ApiWebhooks {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 
     */
    private Long userId;

    /**
     * 
     */
    private Long apiId;

    /**
     * api.updated, usage.threshold, etc.
     */
    private String eventType;

    /**
     * 
     */
    private String targetUrl;

    /**
     * 
     */
    private String secretKey;

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