package generator.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName api_circuit_breakers
 */
@TableName(value ="api_circuit_breakers")
@Data
public class ApiCircuitBreakers {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * Reference to api_target_services
     */
    private Long targetServiceId;

    /**
     * Number of failures before opening circuit
     */
    private Integer failureThreshold;

    /**
     * Number of successes before closing circuit
     */
    private Integer successThreshold;

    /**
     * Time before half-open state
     */
    private Integer timeoutMs;

    /**
     * 0-inactive, 1-active
     */
    private Integer isActive;

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