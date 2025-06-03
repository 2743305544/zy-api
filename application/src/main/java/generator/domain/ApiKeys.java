package generator.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName api_keys
 */
@TableName(value ="api_keys")
@Data
public class ApiKeys {
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
    private String apiKey;

    /**
     * 
     */
    private String name;

    /**
     * 0-disabled, 1-enabled
     */
    private Integer status;

    /**
     * 
     */
    private Date expiresAt;

    /**
     * 
     */
    private Date createdAt;

    /**
     * 
     */
    private Date updatedAt;
}