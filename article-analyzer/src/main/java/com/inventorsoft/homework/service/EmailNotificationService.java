package com.inventorsoft.homework.service;

import com.inventorsoft.homework.entity.User;
import org.springframework.stereotype.Service;

@Service
public class EmailNotificationService implements NotificationService {

  @Override
  public void notifyUser(User user, String message) {
    System.out.println("Sending email to " + user.getEmail() + ": " + message);
  }
}
