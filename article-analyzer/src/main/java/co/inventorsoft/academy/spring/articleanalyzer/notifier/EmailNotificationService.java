package co.inventorsoft.academy.spring.articleanalyzer.notifier;

import co.inventorsoft.academy.spring.articleanalyzer.model.User;
import co.inventorsoft.academy.spring.articleanalyzer.repository.UserRepository;
import com.google.gson.Gson;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

@Service("emailNotificationService")
public class EmailNotificationService extends NotificationService {
    public EmailNotificationService(UserRepository userRepository, Gson gson, ApplicationContext context) {
        super(userRepository, gson, context);
    }

    @Override
    protected void notifyUser(User user, String content) {
        System.out.printf("    Sending email ---> Header: Hello user %s! The result is here | Content: %s%n", user.getEmail(), content);
    }
}