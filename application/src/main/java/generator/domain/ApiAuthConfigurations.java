package generator.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName api_auth_configurations
 */
@TableName(value ="api_auth_configurations")
@Data
public class ApiAuthConfigurations {
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
     * none, api_key, basic, oauth, jwt, custom
     */
    private String authType;

    /**
     * header, query_param, body
     */
    private String authLocation;

    /**
     * Name of parameter for auth (e.g. Authorization)
     */
    private String authKey;

    /**
     * Value or template for auth
     */
    private String authValue;

    /**
     * Additional configuration as JSON
     */
    private Object additionalConfig;

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