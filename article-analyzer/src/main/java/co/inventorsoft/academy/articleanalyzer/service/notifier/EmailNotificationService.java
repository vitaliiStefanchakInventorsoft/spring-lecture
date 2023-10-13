package co.inventorsoft.academy.articleanalyzer.service.notifier;

import org.springframework.stereotype.Component;

import java.util.List;

@Component("EMAIL")
public class EmailNotificationService extends NotificationService {
    @Override
    public void notifyUsers(List<String> categories) {
        System.out.println("----> Email");
        System.out.println(categories);
    }
}
