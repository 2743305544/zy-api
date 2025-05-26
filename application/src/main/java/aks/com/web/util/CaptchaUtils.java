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
}
