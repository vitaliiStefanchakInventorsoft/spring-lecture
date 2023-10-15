package co.inventorsoft.academy.articleanalyzer.service.notifier;

import org.springframework.stereotype.Component;

import java.util.List;

@Component("SLACK")
public class SlackNotificationService extends NotificationService {
    @Override
    protected void notifyUsers(List<String> categories) {
        System.out.println("----> Slack");
        System.out.println(categories);
    }
}
