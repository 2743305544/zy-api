package aks.com.web.controller;

import aks.com.sdk.resp.RespEntity;
import aks.com.web.annotation.RateLimit;
import aks.com.web.cache.CaptchaCache;
import aks.com.web.domain.common.vo.CaptchaVo;
import com.wf.captcha.SpecCaptcha;
import com.wf.captcha.base.Captcha;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * @author Shi Yi
 * @date 2025/5/26
 * @Description 验证码控制器
 */
@RestController
@RequestMapping("/captcha")
@Tag(name = "验证码接口", description = "验证码相关接口")
public class CaptchaController {

    @Resource
    private CaptchaCache captchaCache;

    @GetMapping("/generate")
    @Operation(summary = "生成验证码", description = "生成图形验证码，返回验证码图片和唯一标识")
    public RespEntity<CaptchaVo> generateCaptcha() {
        SpecCaptcha specCaptcha = new SpecCaptcha(130, 48, 5);
        specCaptcha.setCharType(Captcha.TYPE_DEFAULT);
        String captchaValue = specCaptcha.text().toLowerCase();
        String captchaKey = UUID.randomUUID().toString();
        // 将验证码存入Caffeine缓存
        captchaCache.put(captchaKey, captchaValue);

        CaptchaVo captchaVo = CaptchaVo.builder()
                .captchaKey(captchaKey)
                .captchaImage(specCaptcha.toBase64())
                .build();

        return RespEntity.success(captchaVo);
    }
}
