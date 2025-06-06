package aks.com.web.event;

import aks.com.web.service.EmailService;
import generator.service.UserPointsService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * 用户注册事件监听器
 */
@Slf4j
@Component
public class UserRegistrationEventListener {

    @Resource
    private UserPointsService userPointsService;

    @Resource
    private EmailService emailService;

    @EventListener(UserRegistrationEvent.class)
    public void handleUserRegistrationEvent(UserRegistrationEvent event) {
        // 获取注册的用户信息
        var user = event.getUser();

        // 初始积分
        userPointsService.updateUserPoints(user.getId(), 100);


        // 记录用户注册日志
        log.info("新用户注册成功: userId={}, username={}, email={}",
                user.getId(), user.getUsername(), user.getEmail());

        // 发送欢迎邮件
        emailService.sendWelcomeEmail(user);
    }
}
