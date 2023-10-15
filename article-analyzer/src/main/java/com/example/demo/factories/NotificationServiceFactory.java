package com.example.demo.factories;

import com.example.demo.enums.NotificationType;
import com.example.demo.service.EmailNotificationService;
import com.example.demo.service.NotificationService;
import com.example.demo.service.SlackNotificationService;
import org.springframework.stereotype.Component;

@Component
public class NotificationServiceFactory {

    private EmailNotificationService emailNotificationService;

    private SlackNotificationService slackNotificationService;

    public NotificationServiceFactory(EmailNotificationService emailNotificationService, SlackNotificationService slackNotificationService) {
        this.emailNotificationService = emailNotificationService;
        this.slackNotificationService = slackNotificationService;
    }

    public NotificationService createNotificationService(NotificationType notificationType) {
        switch (notificationType) {
            case EMAIL -> {
                return emailNotificationService;
            }
            case SLACK -> {
                return slackNotificationService;
            }
            default -> {
                throw new IllegalArgumentException("Unsupported notification type " + notificationType);
            }
        }
    }

}
