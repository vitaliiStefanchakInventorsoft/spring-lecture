package com.articleanalyzer.core.services;

import com.articleanalyzer.core.models.NotificationType;
import com.articleanalyzer.core.models.User;
import com.articleanalyzer.core.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserNotifier {
    private UserRepository userRepository;
    private EmailNotificationService emailNotificationService;
    private SlackNotificationService slackNotificationService;

    public UserNotifier(UserRepository userRepository,
                        EmailNotificationService emailNotificationService,
                        SlackNotificationService slackNotificationService) {
        this.userRepository = userRepository;
        this.emailNotificationService = emailNotificationService;
        this.slackNotificationService = slackNotificationService;
    }

    public void notifyUsers() {
        for (User user : userRepository.fetchUsers()) {
            if (user.getNotificationType() == NotificationType.EMAIL) {
                emailNotificationService.notifyUser(user);
            } else {
                slackNotificationService.notifyUser(user);
            }
        }
    }
}
