package co.inventorsoft.academy.spring.notifier;

import co.inventorsoft.academy.spring.model.Subscriber;

public class EmailNotificationService extends NotificationService {

    @Override
    protected void notifySubscriber(Subscriber subscriber, String content) {
        System.out.printf("    Sending email ---> Header: Hello subscriber %s! The new article is here | Content: %s%n", subscriber.getEmail(), content);
    }
}
