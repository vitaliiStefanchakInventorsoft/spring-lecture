package com.articleanalyzer.core.services;

import com.articleanalyzer.core.models.User;
import org.springframework.stereotype.Service;

@Service
public class SlackNotificationService extends NotificationService {
    @Override
    public void notifyUser(User user) {
        System.out.println("===== Slack notification.User " + user + " =====");
    }
}
