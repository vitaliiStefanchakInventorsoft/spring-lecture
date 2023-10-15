package co.inventorsoft.academy.spring.notifier;

import co.inventorsoft.academy.spring.Service.UserService;
import co.inventorsoft.academy.spring.model.User;
import co.inventorsoft.academy.spring.notifier.impl.EmailNotificationService;
import co.inventorsoft.academy.spring.notifier.impl.SlackNotificationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class NotificationManager {
    private final UserService userService;
    private final EmailNotificationService emailNotificationService;
    private final SlackNotificationService slackNotificationService;

    public void sendNotification() {
        List<User> users = userService.getUsers();
        users.forEach(user -> {
            switch (user.getNotificationType()) {
                case EMAIL -> emailNotificationService.notify(user, "Email notification");
                case SLACK -> slackNotificationService.notify(user, "Slack message notification");
                default -> System.out.println("Unsupported type notification");
            }
        });
    }
}
