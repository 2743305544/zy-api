package generator.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName point_transactions
 */
@TableName(value ="point_transactions")
@Data
public class PointTransactions {
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
     * recharge-充值, consume-消费, reward-奖励, refund-退款
     */
    private String type;

    /**
     * 积分变动数量，正数表示增加，负数表示减少
     */
    private Integer amount;

    /**
     * 交易后的积分余额
     */
    private Integer balanceAfter;

    /**
     * 交易描述
     */
    private String description;

    /**
     * 关联ID，根据type不同关联不同表
     */
    private Long referenceId;

    /**
     * 关联类型，如api_call, recharge_order等
     */
    private String referenceType;

    /**
     * 0-not deleted, 1-deleted
     */
    private Integer isDeleted;

    /**
     * 
     */
    private Date createdAt;
}