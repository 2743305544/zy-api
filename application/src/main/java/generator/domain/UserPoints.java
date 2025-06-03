package generator.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName user_points
 */
@TableName(value ="user_points")
@Data
public class UserPoints {
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
     * 当前可用积分余额
     */
    private Integer balance;

    /**
     * 历史累计获得的总积分
     */
    private Integer totalEarned;

    /**
     * 历史累计消费的总积分
     */
    private Integer totalConsumed;

    /**
     * 
     */
    private Date lastUpdated;

    /**
     * 
     */
    private Date createdAt;
}