package co.inventorsoft.academy.homework.nofifier;

import co.inventorsoft.academy.homework.model.User;
import org.springframework.stereotype.Component;

@Component
public class EmailNotificationService extends NotificationService {

    @Override
    protected void notifyUserByEmail(User user, String content) {
        System.out.printf("    Sending email ---> Header: Hello %s! The new article is here | Content: %s%n", user.getUsername(), content);
    }

    @Override
    protected void notifyUserBySlack(User user, String content) {
        System.out.println("EmailNotificationService does not support Slack notifications.");
    }
}
