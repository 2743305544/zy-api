package aks.com.web.aspectj;

import aks.com.sdk.exception.ServiceException;
import aks.com.sdk.util.ip.IpUtils;
import aks.com.web.annotation.RateLimit;
import aks.com.web.cache.RateLimitCache;
import aks.com.web.domain.common.entity.RateLimitInfo;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.core.ParameterNameDiscoverer;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.lang.reflect.Method;
import java.time.LocalDateTime;

/**
 * @author Shi Yi
 * @date 2025/5/27
 * @Description 接口访问频率限制切面
 */
@Aspect
@Component
public class RateLimitAspect {

    private final ExpressionParser parser = new SpelExpressionParser();
    private final ParameterNameDiscoverer nameDiscoverer = new DefaultParameterNameDiscoverer();

    @Resource
    private RateLimitCache rateLimitCache;

    @Before("@annotation(rateLimit)")
    public void before(JoinPoint joinPoint, RateLimit rateLimit) {
        // 获取请求信息
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attributes == null) {
            return;
        }
        HttpServletRequest request = attributes.getRequest();

        // 获取限流key
        String limitKey = getLimitKey(joinPoint, rateLimit, request);

        // 获取当前访问次数
        RateLimitInfo rateLimitInfo = rateLimitCache.get(limitKey);

        if (rateLimitInfo != null) {
            // 检查是否在同一时间窗口内
            LocalDateTime now = LocalDateTime.now();
            long secondsElapsed = rateLimitInfo.getStartTime().plusSeconds(rateLimit.timeUnit().toSeconds(rateLimit.period())).isAfter(now) ? 0 : 1;

            // 如果不在同一时间窗口，重置计数
            if (secondsElapsed > 0) {
                rateLimitInfo = new RateLimitInfo(now, 1);
            } else {
                // 如果在同一时间窗口且已达到最大访问次数，拒绝访问
                if (rateLimitInfo.getCount() >= rateLimit.count()) {
                    throw new ServiceException(rateLimit.message());
                }

                // 增加访问次数
                rateLimitInfo.setCount(rateLimitInfo.getCount() + 1);
            }
        } else {
            // 首次访问，创建新的限流信息
            rateLimitInfo = new RateLimitInfo(LocalDateTime.now(), 1);
        }

        // 更新缓存
        rateLimitCache.put(limitKey, rateLimitInfo, rateLimit.timeUnit().toSeconds(rateLimit.period()));
    }

    /**
     * 获取限流的key
     */
    private String getLimitKey(JoinPoint joinPoint, RateLimit rateLimit, HttpServletRequest request) {
        StringBuilder sb = new StringBuilder(rateLimit.prefix());

        // 如果key为空，则使用请求IP作为key
        if (rateLimit.key().isEmpty()) {
            sb.append(IpUtils.getIpAddress(request));
            sb.append(":");
            sb.append(joinPoint.getSignature().getName());
        } else {
            // 使用SpEL表达式解析key
            String spelKey = rateLimit.key();

            // 如果是使用IP
            if ("#ip".equals(spelKey)) {
                sb.append(IpUtils.getIpAddress(request));
            } else {
                // 解析SpEL表达式
                MethodSignature signature = (MethodSignature) joinPoint.getSignature();
                Method method = signature.getMethod();
                Object[] args = joinPoint.getArgs();

                // 获取方法参数名
                String[] paramNames = nameDiscoverer.getParameterNames(method);
                if (paramNames != null) {
                    EvaluationContext context = new StandardEvaluationContext();

                    // 设置表达式变量
                    for (int i = 0; i < paramNames.length; i++) {
                        context.setVariable(paramNames[i], args[i]);
                    }

                    // 解析表达式
                    Expression expression = parser.parseExpression(spelKey);
                    Object value = expression.getValue(context);
                    if (value != null) {
                        sb.append(value);
                    }
                }
            }
        }

        return sb.toString();
    }

}
