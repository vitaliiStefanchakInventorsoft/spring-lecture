package com.example.demo.service.notifications;

import com.example.demo.model.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class EmailNotificationService implements NotificationService {
  @Override
  public void sendNotification(Message message) {
    log.info("mail with text: {}  was sent to {}",message.getText(), message.getReceiver().getEmail());
  }
}
