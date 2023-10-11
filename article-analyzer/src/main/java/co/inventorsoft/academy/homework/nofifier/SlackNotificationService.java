package co.inventorsoft.academy.homework.nofifier;

import co.inventorsoft.academy.homework.model.User;
import org.springframework.stereotype.Component;

@Component
public class SlackNotificationService extends NotificationService{
    @Override
    protected void notifyUserByEmail(User user, String content) {
        System.out.println("SlackNotificationService does not support Email notifications.");
    }

    @Override
    protected void notifyUserBySlack(User user, String content) {
        System.out.printf("    Sending Slack notification ---> Header: Hello %s! The new article is here | Content: %s%n", user.getUsername(), content);
    }
}
