package aks.com.sdk.resp;

import lombok.Getter;

/**
 * @author xxl
 * @since 2023/11/23
 */
@Getter
public enum HttpCode {
    SUCCESS(200, "success"),
    UNAUTHORIZED(401, "unauthorized"),
    FORBIDDEN(403, "forbidden"),
    INTERNAL_SERVER_ERROR(500, "internal server error"),
    USERNAME_PASSWORD_ERROR(1001, "username or password error"),
    CAPTCHA_ERROR(1002, "验证码错误或已过期"),
    EMAIL_EMPTY(1003,"邮箱不能为空")
    ,EMAIL_ERROR(1003,"邮箱格式错误");

    private final String reasonPhrase;
    private final int code;

    HttpCode(int i, String s) {
        reasonPhrase = s;
        code = i;
    }
}
