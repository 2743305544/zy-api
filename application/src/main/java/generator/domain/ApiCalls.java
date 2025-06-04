package generator.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName api_calls
 */
@TableName(value ="api_calls")
@Data
public class ApiCalls {
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
     * 
     */
    private Long endpointId;

    /**
     * 
     */
    private Long apiKeyId;

    /**
     * 
     */
    private Date requestTime;

    /**
     * 响应时间（毫秒）
     */
    private Integer responseTime;

    /**
     * 
     */
    private Integer statusCode;

    /**
     * 消费的积分数
     */
    private Integer pointsConsumed;

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

    /**
     * 
     */
    private Date createdAt;
}