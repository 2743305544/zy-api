package aks.com.web.controller;

import aks.com.sdk.exception.ServiceException;
import aks.com.sdk.resp.HttpCode;
import aks.com.sdk.resp.RespEntity;
import aks.com.web.annotation.RateLimit;
import aks.com.web.cache.CaptchaCache;
import aks.com.web.service.EmailService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;
import java.util.UUID;

/**
 * @author Shi Yi
 * @date 2025/5/27
 * @Description 邮箱验证码控制器
 */
@RestController
@RequestMapping("/email-captcha")
@Tag(name = "邮箱验证码接口", description = "邮箱验证码相关接口")
public class EmailCaptchaController {

    @Resource
    private CaptchaCache captchaCache;

    @Resource
    private EmailService emailService;

    @GetMapping("/send")
    @Operation(summary = "发送邮箱验证码", description = "向指定邮箱发送验证码")
    @RateLimit(key = "#email", period = 60, count = 1, message = "邮箱验证码发送过于频繁，请1分钟后再试")
    public RespEntity<String> sendEmailCaptcha(
            @Parameter(description = "邮箱地址", required = true)
            @RequestParam(value = "email") String email) {

        if (!StringUtils.hasText(email)) {
            throw new ServiceException(HttpCode.EMAIL_EMPTY);
        }

        if (!emailService.isValidEmail(email)) {
            throw new ServiceException(HttpCode.EMAIL_ERROR);
        }

        // 生成6位数字验证码
        String captchaValue = generateNumericCaptcha(6);
        String captchaKey = UUID.randomUUID().toString();

        // 将验证码存入Caffeine缓存，使用相同的缓存机制
        captchaCache.put(captchaKey, captchaValue);

        // 发送邮件
        emailService.sendVerificationCode(email, captchaValue);

        return RespEntity.success(captchaKey);
    }

    /**
     * 生成指定长度的数字验证码
     *
     * @param length 验证码长度
     * @return 数字验证码
     */
    private String generateNumericCaptcha(int length) {
        Random random = new Random();
        StringBuilder captcha = new StringBuilder();
        for (int i = 0; i < length; i++) {
            captcha.append(random.nextInt(10));
        }
        return captcha.toString();
    }
}
