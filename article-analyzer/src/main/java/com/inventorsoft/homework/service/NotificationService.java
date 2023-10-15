package com.inventorsoft.homework.service;

import com.inventorsoft.homework.entity.User;
public interface NotificationService {
  void notifyUser(User user, String message);
}
