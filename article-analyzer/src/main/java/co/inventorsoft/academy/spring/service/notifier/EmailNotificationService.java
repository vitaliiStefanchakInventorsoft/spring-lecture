package co.inventorsoft.academy.spring.service.notifier;

import co.inventorsoft.academy.spring.model.User;
import org.springframework.stereotype.Component;

@Component
public class EmailNotificationService extends NotificationService {
    @Override
    public void notifyUsers(User user, String categories) {
        System.out.printf(">>> Sending email ---> Header: Hello subscriber %s! Here is a list of categories | Content: %s%n", user.getUsername(), categories);
    }
}
