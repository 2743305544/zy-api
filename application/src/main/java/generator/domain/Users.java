package generator.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 *
 * @TableName users
 */
@TableName(value ="users")
@Data
@Accessors(chain = true)
public class Users {
    /**
     *
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     *
     */
    private String username;

    /**
     *
     */
    private String password;

    /**
     *
     */
    private String email;

    /**
     *
     */
    private String fullName;

    /**
     *
     */
    private String phone;

    /**
     * 0-disabled, 1-enabled
     */
    private Integer status;

    /**
     * admin, user, developer
     */
    private String role;

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
