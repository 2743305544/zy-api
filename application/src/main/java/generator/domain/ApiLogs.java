package generator.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName api_logs
 */
@TableName(value ="api_logs")
@Data
public class ApiLogs {
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
    private Long userId;

    /**
     * 
     */
    private String action;

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