package generator.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName point_recharge_orders
 */
@TableName(value ="point_recharge_orders")
@Data
public class PointRechargeOrders {
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
     * 订单编号
     */
    private String orderNo;

    /**
     * 充值金额
     */
    private BigDecimal amount;

    /**
     * 充值积分数量
     */
    private Integer points;

    /**
     * 支付方式：alipay, wechat, bank_transfer等
     */
    private String paymentMethod;

    /**
     * pending-待支付, paid-已支付, cancelled-已取消, refunded-已退款
     */
    private String paymentStatus;

    /**
     * 支付时间
     */
    private Date paymentTime;

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