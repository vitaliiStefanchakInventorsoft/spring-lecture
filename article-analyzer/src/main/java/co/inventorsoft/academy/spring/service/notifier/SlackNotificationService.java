package co.inventorsoft.academy.spring.service.notifier;

import co.inventorsoft.academy.spring.model.User;

public class SlackNotificationService extends NotificationService {
    @Override
    public void notifyUsers(User user, String categories) {
        System.out.printf(">>> Sending Slack message ---> Header: Hello subscriber %s! Here is a list of categories | Content: %s%n", user.getSlackId(), categories);
    }
}
