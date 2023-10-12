package co.inventorsoft.academy.spring.service;

import co.inventorsoft.academy.spring.model.User;

import org.springframework.stereotype.Service;

@Service
public interface NotificationService {
    void notifyUser(User user, String content);
}
