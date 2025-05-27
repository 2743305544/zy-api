package aks.com.web.annotation;

import java.lang.annotation.*;
import java.util.concurrent.TimeUnit;

/**
 * @author Shi Yi
 * @date 2025/5/27
 * @Description 接口访问频率限制注解
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RateLimit {
    
    /**
     * 限流的key前缀
     */
    String prefix() default "rate_limit:";
    
    /**
     * 限流的key，支持SpEL表达式
     * 例如：#ip 表示使用请求IP作为key
     * 例如：#email 表示使用请求参数中的email作为key
     */
    String key() default "";
    
    /**
     * 时间窗口，默认60秒
     */
    int period() default 60;
    
    /**
     * 时间单位，默认秒
     */
    TimeUnit timeUnit() default TimeUnit.SECONDS;
    
    /**
     * 在时间窗口内允许的最大请求次数
     */
    int count() default 1;
    
    /**
     * 达到限制时的提示消息
     */
    String message() default "操作过于频繁，请稍后再试";
}
