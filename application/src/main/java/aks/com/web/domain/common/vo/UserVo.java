package aks.com.web.domain.common.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Shi Yi
 * @date 2025/5/26
 * @Description
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserVo {
    private Long id;
    /**
     *
     */
    private String username;

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
     * admin, user, developer
     */
    private String role;


    private String token;
}
