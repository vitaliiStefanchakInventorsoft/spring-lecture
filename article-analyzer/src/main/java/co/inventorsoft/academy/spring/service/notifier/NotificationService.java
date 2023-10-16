package co.inventorsoft.academy.spring.service.notifier;

import co.inventorsoft.academy.spring.model.User;
import org.springframework.stereotype.Component;

@Component
public abstract class NotificationService {

    public abstract void notifyUsers(User user, String categories);
}
