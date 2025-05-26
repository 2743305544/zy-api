package aks.com.web.cache;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @author Shi Yi
 * @date 2025/5/26
 * @Description 验证码缓存
 */
@Component
public class CaptchaCache {

    private static final long CAPTCHA_EXPIRATION = 5;

    private final Cache<String, String> captchaCache = Caffeine.newBuilder()
            .expireAfterWrite(CAPTCHA_EXPIRATION, TimeUnit.MINUTES)
            .maximumSize(10000)
            .build();

    private static final String CAPTCHA_PREFIX = "captcha:";

    /**
     * 存储验证码
     *
     * @param key   验证码唯一标识
     * @param value 验证码值
     */
    public void put(String key, String value) {
        captchaCache.put(CAPTCHA_PREFIX + key, value);
    }

    /**
     * 获取验证码
     *
     * @param key 验证码唯一标识
     * @return 验证码值
     */
    public String get(String key) {
        return captchaCache.getIfPresent(CAPTCHA_PREFIX + key);
    }

    /**
     * 删除验证码
     *
     * @param key 验证码唯一标识
     */
    public void remove(String key) {
        captchaCache.invalidate(CAPTCHA_PREFIX + key);
    }
}
