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
 * @TableName membership_orders
 */
@TableName(value ="membership_orders")
@Data
public class MembershipOrders {
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
    private Long levelId;

    /**
     * 订单编号
     */
    private String orderNo;

    /**
     * month-月度, year-年度
     */
    private String periodType;

    /**
     * 支付金额
     */
    private BigDecimal amount;

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
     * 会员开始时间
     */
    private Date startDate;

    /**
     * 会员到期时间
     */
    private Date expiryDate;

    /**
     * 
     */
    private Date createdAt;

    /**
     * 
     */
    private Date updatedAt;
}