package co.inventorsoft.academy.spring.notifier.impl;

import co.inventorsoft.academy.spring.model.User;
import co.inventorsoft.academy.spring.notifier.NotificationService;
import org.springframework.stereotype.Service;

@Service
public class EmailNotificationService implements NotificationService {
    @Override
    public void notifyUser(User user, String content) {
        System.out.printf("Sending email ---> Header: Hello user %s! The new article is here | Content: %s%n", user.getEmail(), content);
    }
}
