package aks.com.web.util;

import aks.com.sdk.exception.ServiceException;
import aks.com.sdk.resp.HttpCode;
import aks.com.web.cache.CaptchaCache;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * @author Shi Yi
 * @date 2025/5/26
 * @Description 验证码工具类
 */
@Component
public class CaptchaUtils {

    @Resource
    private CaptchaCache captchaCache;

    /**
     * 验证验证码
     *
     * @param captchaKey  验证码唯一标识
     * @param captchaCode 用户输入的验证码
     * @return 验证结果
     */
    public boolean validateCaptcha(String captchaKey, String captchaCode) {
        if (!StringUtils.hasText(captchaKey) || !StringUtils.hasText(captchaCode)) {
            return false;
        }

        String storedCaptcha = captchaCache.get(captchaKey);

        if (storedCaptcha == null) {
            return false;
        }

        // 验证成功后删除验证码
        boolean isValid = storedCaptcha.equalsIgnoreCase(captchaCode);
        if (isValid) {
            captchaCache.remove(captchaKey);
        }

        return isValid;
    }

    /**
     * 验证验证码并抛出异常
     *
     * @param captchaKey  验证码唯一标识
     * @param captchaCode 用户输入的验证码
     */
    public void validateCaptchaWithException(String captchaKey, String captchaCode) {
        if (!validateCaptcha(captchaKey, captchaCode)) {
            throw new ServiceException(HttpCode.CAPTCHA_ERROR);
        }
    }

    /**
     * 验证邮箱验证码
     *
     * @param captchaKey  验证码唯一标识
     * @param captchaCode 用户输入的验证码
     * @param email       用户邮箱
     * @return 验证结果
     */
    public boolean validateEmailCaptcha(String captchaKey, String captchaCode, String email) {
        if (!StringUtils.hasText(captchaKey) || !StringUtils.hasText(captchaCode) || !StringUtils.hasText(email)) {
            return false;
        }

        String storedValue = captchaCache.get(captchaKey);

        if (storedValue == null) {
            return false;
        }

        // 解析存储的值，格式为 "email:验证码"
        String[] parts = storedValue.split(":", 2);
        if (parts.length != 2) {
            return false;
        }

        String storedEmail = parts[0];
        String storedCaptcha = parts[1];

        // 验证邮箱和验证码是否匹配
        boolean isValid = storedEmail.equals(email) && storedCaptcha.equals(captchaCode);
        if (isValid) {
            captchaCache.remove(captchaKey);
        }

        return isValid;
    }

    /**
     * 验证邮箱验证码并抛出异常
     *
     * @param captchaKey  验证码唯一标识
     * @param captchaCode 用户输入的验证码
     * @param email       用户邮箱
     */
    public void validateEmailCaptchaWithException(String captchaKey, String captchaCode, String email) {
        if (!validateEmailCaptcha(captchaKey, captchaCode, email)) {
            throw new ServiceException(HttpCode.CAPTCHA_ERROR);
        }
    }
}
