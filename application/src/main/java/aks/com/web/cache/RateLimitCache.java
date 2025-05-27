package aks.com.web.cache;

import aks.com.web.domain.common.entity.RateLimitInfo;
import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @author Shi Yi
 * @date 2025/5/27
 * @Description 限流缓存
 */
@Component
public class RateLimitCache {

    private static final long DEFAULT_EXPIRATION = 60;
    private static final String RATE_LIMIT_PREFIX = "rate_limit:";

    private final Cache<String, RateLimitInfo> rateLimitCache = Caffeine.newBuilder()
            .expireAfterWrite(DEFAULT_EXPIRATION, TimeUnit.SECONDS)
            .maximumSize(10000)
            .build();

    /**
     * 存储限流信息
     *
     * @param key   限流唯一标识
     * @param value 限流信息
     */
    public void put(String key, RateLimitInfo value) {
        rateLimitCache.put(RATE_LIMIT_PREFIX + key, value);
    }

    /**
     * 存储限流信息，并指定过期时间
     *
     * @param key      限流唯一标识
     * @param value    限流信息
     * @param duration 过期时间（秒）
     */
    public void put(String key, RateLimitInfo value, long duration) {
        // 由于Caffeine不支持对单个key设置过期时间，这里使用默认过期时间
        // 实际过期逻辑在RateLimitAspect中处理
        rateLimitCache.put(RATE_LIMIT_PREFIX + key, value);
    }

    /**
     * 获取限流信息
     *
     * @param key 限流唯一标识
     * @return 限流信息
     */
    public RateLimitInfo get(String key) {
        return rateLimitCache.getIfPresent(RATE_LIMIT_PREFIX + key);
    }

    /**
     * 删除限流信息
     *
     * @param key 限流唯一标识
     */
    public void remove(String key) {
        rateLimitCache.invalidate(RATE_LIMIT_PREFIX + key);
    }
}
