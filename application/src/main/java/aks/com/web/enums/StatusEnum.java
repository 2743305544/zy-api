package aks.com.web.enums;

import lombok.Getter;

/**
 * @author Shi Yi
 * @date 2025/5/26
 * @Description
 */
@Getter
public enum StatusEnum {
    /**
     * 正常
     */
    NORMAL(1, "正常"),
    /**
     * 禁用
     */
    DISABLED(0, "禁用");

    private final Integer code;
    private final String message;

    StatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
