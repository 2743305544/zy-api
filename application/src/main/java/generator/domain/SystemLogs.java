package generator.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName system_logs
 */
@TableName(value ="system_logs")
@Data
public class SystemLogs {
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
    private String action;

    /**
     * 操作的实体类型，如user, api, order等
     */
    private String entityType;

    /**
     * 操作的实体ID
     */
    private Long entityId;

    /**
     * 
     */
    private String details;

    /**
     * 
     */
    private String ipAddress;

    /**
     * 0-not deleted, 1-deleted
     */
    private Integer isDeleted;

    /**
     * 
     */
    private Date createdAt;
}