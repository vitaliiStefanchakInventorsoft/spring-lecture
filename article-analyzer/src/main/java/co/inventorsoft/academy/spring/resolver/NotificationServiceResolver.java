package co.inventorsoft.academy.spring.resolver;

import co.inventorsoft.academy.spring.model.NotificationType;
import co.inventorsoft.academy.spring.repository.UserRepository;
import co.inventorsoft.academy.spring.service.EmailNotificationService;
import co.inventorsoft.academy.spring.service.NotificationService;
import co.inventorsoft.academy.spring.service.SlackNotificationService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@Component
public class NotificationServiceResolver {

    /*private EmailNotificationService emailNF;
    private SlackNotificationService slackNF;
    public NotificationService getNotificationService(NotificationType type){
        switch (type) {
            case EMAIL -> {
                return emailNF;
            }
            case SLACK -> {
                return slackNF;
            }
            default -> {
                return null;
            }
        }
    }*/

    private List<Class<? extends NotificationService>> notificationServices;

    @Autowired
    private UserRepository userRepository;

    public NotificationServiceResolver() {
        notificationServices = new ArrayList<>();
        notificationServices.add(SlackNotificationService.class);
        notificationServices.add(EmailNotificationService.class);

    }

    public NotificationService getNotificationService(NotificationType type) throws InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {

        for (Class<? extends NotificationService> serviceClazz : notificationServices) {
            NotificationService nfs = serviceClazz.getDeclaredConstructor().newInstance();
            if (type == nfs.getType()) {
                return nfs;
            }
        }
        return null;
    }

    public void notifyAllUsers() throws FileNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        for (var user : userRepository.fetchAllUsers()) {
            getNotificationService(user.getNotificationType())
                    .notifyUser(user, "asdadfgdsfsd");
        }
    }
}
