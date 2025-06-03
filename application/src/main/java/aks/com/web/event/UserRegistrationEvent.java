package aks.com.web.event;

import generator.domain.Users;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;
import org.springframework.stereotype.Component;

/**
 * 用户注册事件
 */
@Getter
public class UserRegistrationEvent extends ApplicationEvent {

    private final Users user;

    public UserRegistrationEvent(Object source, Users user) {
        super(source);
        this.user = user;
    }
}
