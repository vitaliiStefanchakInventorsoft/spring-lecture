package co.inventorsoft.academy.articleanalyzer.service.notifier;

import co.inventorsoft.academy.articleanalyzer.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
abstract public class NotificationService {
    public void notifyUsers(User user, List<String> categories) {
        System.out.println("The user: " + user.getUserName() + " was informed about the analysis of the categories,");
        System.out.println("the list of categories was sent via:");
        notifyUsers(categories);
        System.out.println("*************************************");
    }

    abstract protected void notifyUsers(List<String> categories);
}
