package aks.com.web.domain.common.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author Shi Yi
 * @date 2025/5/27
 * @Description 限流信息
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RateLimitInfo implements Serializable {

    /**
     * 开始时间
     */
    private LocalDateTime startTime;

    /**
     * 访问次数
     */
    private int count;
}
