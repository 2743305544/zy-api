package aks.com.web.enums;

import lombok.Getter;

/**
 * @author Shi Yi
 * @date 2025/5/27
 * @Description
 */
@Getter
public enum RoleEnum {

    /**
     * 管理员
     */
    ADMIN("admin", "管理员"),
    /**
     * 用户
     */
    USER("user", "用户");


    private final String role;
    private final String message;


    RoleEnum(String role, String message) {
        this.role = role;
        this.message = message;
    }

}
