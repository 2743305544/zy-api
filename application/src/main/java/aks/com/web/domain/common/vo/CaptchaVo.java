package aks.com.web.domain.common.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Shi Yi
 * @date 2025/5/26
 * @Description 验证码返回对象
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "验证码返回对象")
public class CaptchaVo {
    
    /**
     * 验证码唯一标识
     */
    @Schema(description = "验证码唯一标识")
    private String captchaKey;
    
    /**
     * 验证码图片Base64
     */
    @Schema(description = "验证码图片Base64")
    private String captchaImage;
}
