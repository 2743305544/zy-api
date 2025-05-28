package aks.com.web.service;

import aks.com.sdk.exception.ServiceException;
import jakarta.annotation.Resource;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @author Shi Yi
 * @date 2025/5/27
 * @Description 邮件服务
 */
@Service
public class EmailService {

    @Resource
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String fromEmail;

    @Value("${spring.mail.nickname:API平台}")
    private String nickname;

    /**
     * 异步发送验证码邮件
     *
     * @param toEmail 收件人邮箱
     * @param code    验证码
     */
    @Async("virtualThreadExecutor")
    public void sendVerificationCode(String toEmail, String code) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setFrom(String.format("%s <%s>", nickname, fromEmail));
            helper.setTo(toEmail);
            helper.setSubject("API平台 - 邮箱验证码");

            String content = "<div style='padding:20px;background-color:#f7f7f7;'>"
                    + "<h2 style='color:#333;'>知意 API平台 - 邮箱验证码</h2>"
                    + "<p style='font-size:14px;color:#666;'>您好，您正在进行邮箱验证，验证码为：</p>"
                    + "<div style='padding:10px 20px;background-color:#fff;border-radius:5px;margin:15px 0;'>"
                    + "<p style='font-size:24px;font-weight:bold;color:#333;letter-spacing:5px;text-align:center;'>" + code + "</p>"
                    + "</div>"
                    + "<p style='font-size:14px;color:#666;'>验证码有效期为5分钟，请勿泄露给他人。</p>"
                    + "<p style='font-size:12px;color:#999;margin-top:30px;'>此邮件为系统自动发送，请勿回复。</p>"
                    + "</div>";

            helper.setText(content, true);
            mailSender.send(message);
        } catch (MessagingException e) {
            throw new ServiceException("发送邮件失败: " + e.getMessage());
        }
    }

    public boolean isValidEmail(String email) {
        String regex = "\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*";
        return email.matches(regex);
    }
}
