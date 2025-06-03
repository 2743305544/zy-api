package aks.com.web.service;

import aks.com.sdk.exception.ServiceException;
import generator.domain.Users;
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

    /**
     * 异步发送用户注册欢迎邮件
     *
     * @param user 用户对象
     */
    @Async("virtualThreadExecutor")
    public void sendWelcomeEmail(Users user) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setFrom(String.format("%s <%s>", nickname, fromEmail));
            helper.setTo(user.getEmail());
            helper.setSubject("欢迎加入知意API平台");

            String username = user.getUsername();
            if (user.getFullName() != null && !user.getFullName().isEmpty()) {
                username = user.getFullName();
            }

            String content = "<div style='padding:20px;background-color:#f7f7f7;'>"
                    + "<h2 style='color:#333;'>欢迎加入知意API平台</h2>"
                    + "<p style='font-size:14px;color:#666;'>亲爱的 " + username + "，</p>"
                    + "<p style='font-size:14px;color:#666;'>感谢您注册知意API平台！我们很高兴您能加入我们的社区。</p>"
                    + "<div style='padding:15px;background-color:#fff;border-radius:5px;margin:15px 0;'>"
                    + "<p style='font-size:14px;color:#333;'>您的账号已成功创建，并已获得初始积分奖励。</p>"
                    + "<p style='font-size:14px;color:#333;'>您现在可以：</p>"
                    + "<ul style='font-size:14px;color:#333;'>"
                    + "<li>浏览和使用我们提供的各种API</li>"
                    + "<li>管理您的API密钥</li>"
                    + "<li>查看您的积分余额和使用记录</li>"
                    + "<li>升级会员获取更多权益</li>"
                    + "</ul>"
                    + "</div>"
                    + "<p style='font-size:14px;color:#666;'>如果您有任何问题，请随时联系我们的客服团队。</p>"
                    + "<p style='font-size:14px;color:#666;margin-top:20px;'>祝您使用愉快！</p>"
                    + "<p style='font-size:14px;color:#666;'>知意API平台团队</p>"
                    + "<p style='font-size:12px;color:#999;margin-top:30px;border-top:1px solid #ddd;padding-top:10px;'>此邮件为系统自动发送，请勿回复。</p>"
                    + "</div>";

            helper.setText(content, true);
            mailSender.send(message);
        } catch (MessagingException e) {
            throw new ServiceException("发送欢迎邮件失败: " + e.getMessage());
        }
    }

    public boolean isValidEmail(String email) {
        String regex = "\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*";
        return email.matches(regex);
    }
}
