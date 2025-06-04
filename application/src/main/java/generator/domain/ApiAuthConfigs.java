package generator.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName api_auth_configs
 */
@TableName(value ="api_auth_configs")
@Data
public class ApiAuthConfigs {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 
     */
    private Long targetId;

    /**
     * none, basic, bearer, api_key, oauth2
     */
    private String authType;

    /**
     * 认证信息位置：header, query, body
     */
    private String authLocation;

    /**
     * 认证参数名，如Authorization, X-API-Key等
     */
    private String authName;

    /**
     * 认证值或模板
     */
    private String authValue;

    /**
     * 额外参数，JSON格式
     */
    private String additionalParams;

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