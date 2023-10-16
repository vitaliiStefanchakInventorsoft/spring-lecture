package co.inventorsoft.academy.spring.facory;

import co.inventorsoft.academy.spring.model.NotificationType;
import co.inventorsoft.academy.spring.service.notifier.EmailNotificationService;
import co.inventorsoft.academy.spring.service.notifier.NotificationService;
import co.inventorsoft.academy.spring.service.notifier.SlackNotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class NotificationServiceFactory {
    private final EmailNotificationService emailNotificationService;
    private final SlackNotificationService slackNotificationService;

    @Autowired
    public NotificationServiceFactory(EmailNotificationService emailNotificationService, SlackNotificationService slackNotificationService) {
        this.emailNotificationService = emailNotificationService;
        this.slackNotificationService = slackNotificationService;
    }

    public NotificationService getService(NotificationType type) {
        return switch (type) {
            case EMAIL -> emailNotificationService;
            case SLACK -> slackNotificationService;
            default -> throw new IllegalArgumentException("Unknown notification type: " + type);
        };
    }
}