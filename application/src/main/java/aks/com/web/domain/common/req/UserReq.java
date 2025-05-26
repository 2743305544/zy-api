package aks.com.web.domain.common.req;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author Shi Yi
 * @date 2025/5/26
 * @Description
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserReq {
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
     * 验证码唯一标识
     */
    private String captchaKey;
    
    /**
     * 验证码值
     */
    private String captchaCode;
}
