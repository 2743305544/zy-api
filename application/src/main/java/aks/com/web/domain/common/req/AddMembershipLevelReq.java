package aks.com.web.domain.common.req;

import generator.domain.MembershipLevels;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;

/**
 * @author Shi Yi
 * @date 2025/6/4
 * @Description
 */
public class AddMembershipLevelReq {

    /**
     * 会员等级名称，如普通用户、VIP会员、企业会员等
     */
    private String name;

    /**
     * 会员等级描述
     */
    private String description;

    /**
     * 每分钟API调用次数限制
     */
    private Integer apiCallRateLimit;

    /**
     * 积分消费折扣，1.00表示无折扣，0.80表示8折
     */
    private BigDecimal pointDiscount;

    /**
     * 月度会员价格
     */
    private BigDecimal monthlyPrice;

    /**
     * 年度会员价格
     */
    private BigDecimal yearlyPrice;

    /**
     * 0-inactive, 1-active
     */
    private Integer isActive;


    public MembershipLevels toEntity() {
        MembershipLevels membershipLevels = new MembershipLevels();
        BeanUtils.copyProperties(this, membershipLevels);
        return membershipLevels;
    }
}
