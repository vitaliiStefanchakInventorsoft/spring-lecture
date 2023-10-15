package com.articleanalyzer.core.models;

import com.articleanalyzer.core.services.NotificationService;

public class User {
    private int id;
    private String username;
    private String email;
    private String slackId;
    private NotificationType notificationType;

    public String toString() {
        return "name:" + username + ";id:" + id;
    }

    public NotificationType getNotificationType() {
        return notificationType;
    }

}
