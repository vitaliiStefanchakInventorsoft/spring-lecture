package com.example.demo.service.notifications;

import com.example.demo.model.Message;
import com.example.demo.model.NotificationType;

public interface NotificationService {
  void sendNotification(Message message);
}

