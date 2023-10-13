package co.inventorsoft.academy.spring.notifier;

import co.inventorsoft.academy.spring.model.User;

public interface NotificationService {
    void notifyUser(User user, String content);
}
