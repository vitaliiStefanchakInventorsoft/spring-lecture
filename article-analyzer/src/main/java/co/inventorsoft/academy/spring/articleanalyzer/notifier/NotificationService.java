package co.inventorsoft.academy.spring.articleanalyzer.notifier;

import co.inventorsoft.academy.spring.articleanalyzer.model.NotificationType;
import co.inventorsoft.academy.spring.articleanalyzer.model.User;
import co.inventorsoft.academy.spring.articleanalyzer.repository.UserRepository;
import com.google.gson.Gson;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Set;

@Service("notificationService")
public abstract class NotificationService {
    private final UserRepository userRepository;
    private final Gson gson;
    private final ApplicationContext context;

    public NotificationService(UserRepository userRepository, Gson gson, ApplicationContext context) {
        this.userRepository = userRepository;
        this.gson = gson;
        this.context = context;
    }

    // Notify all users about the result
    public void notify(Set<String> result) {
        System.out.println("Notifying about result...");
        String jsonResult = gson.toJson(result);
        List<User> users = userRepository.fetchAllUsers();
        users.forEach(user -> {
            NotificationType notificationType = user.getNotificationType();
            NotificationService strategy = context.getBean(notificationType.getName()
                    + "NotificationService", NotificationService.class);
            strategy.notifyUser(user, jsonResult);
            });
    }

    // Notify a single user about the result
    protected abstract void notifyUser(User user, String content);
}
