package generator.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName api_usage_stats
 */
@TableName(value ="api_usage_stats")
@Data
public class ApiUsageStats {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 
     */
    private Long apiKeyId;

    /**
     * 
     */
    private Long apiId;

    /**
     * 
     */
    private Long endpointId;

    /**
     * 
     */
    private Date requestTimestamp;

    /**
     * Response time in milliseconds
     */
    private Integer responseTime;

    /**
     * 
     */
    private Integer statusCode;

    /**
     * 
     */
    private Integer requestSize;

    /**
     * 
     */
    private Integer responseSize;

    /**
     * 
     */
    private String ipAddress;

    /**
     * 
     */
    private String userAgent;

    /**
     * 0-not deleted, 1-deleted
     */
    private Integer isDeleted;
}