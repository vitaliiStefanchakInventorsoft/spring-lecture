package co.inventorsoft.academy.spring.service.notifier;

import co.inventorsoft.academy.spring.model.User;

@Component
public abstract class NotificationService {

    protected abstract void notifyUsers(User user, String categories);
}
