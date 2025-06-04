package aks.com.web.domain.common.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import generator.domain.MembershipLevels;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Shi Yi
 * @date 2025/6/4
 * @Description
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MembershipLevelsVo {

    /**
     *
     */
    private Long id;

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


    public MembershipLevelsVo(MembershipLevels membershipLevels) {
        BeanUtils.copyProperties(membershipLevels, this);
    }

    public MembershipLevels toEntity() {
        MembershipLevels entity = new MembershipLevels();
        BeanUtils.copyProperties(this, entity);
        return entity;
    }
}
