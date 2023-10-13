package co.inventorsoft.academy.spring.notifier;

import co.inventorsoft.academy.spring.model.User;
import co.inventorsoft.academy.spring.notifier.impl.EmailNotificationService;
import co.inventorsoft.academy.spring.notifier.impl.SlackNotificationService;
import co.inventorsoft.academy.spring.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class NotificationManager {
    private final UserService userService;
    private final EmailNotificationService emailNotificationService;
    private final SlackNotificationService slackNotificationService;

    public void sendNotifications() {
        List<User> users = userService.getUsers();
        if (!users.isEmpty()) {
            users.forEach(user -> {
                switch (user.getNotificationType()) {
                    case EMAIL -> emailNotificationService.notifyUser(user, "Your email notification message");
                    case SLACK -> slackNotificationService.notifyUser(user, "Your Slack notification message");
                    default -> System.out.println("Unsupported notification type for user: " + user.getUsername());
                }
            });
        }
    }
}
