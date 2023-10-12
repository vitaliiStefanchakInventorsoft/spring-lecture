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
import java.util.Objects;

@Data
@AllArgsConstructor
@Component
public class NotificationServiceResolver {

    private List<Class<? extends NotificationService>> notificationServices;

    @Autowired
    private UserRepository userRepository;

    public NotificationServiceResolver() {
        notificationServices = new ArrayList<>();
        notificationServices.add(SlackNotificationService.class);
        notificationServices.add(EmailNotificationService.class);

    }

    public NotificationService getNotificationService(NotificationType type){

        for (Class<? extends NotificationService> serviceClazz : notificationServices) {
            NotificationService nfs = null;
            try {
                nfs = serviceClazz.getDeclaredConstructor().newInstance();
            } catch (InstantiationException |
                     IllegalAccessException |
                     InvocationTargetException |
                     NoSuchMethodException e) {
                e.getStackTrace();
            }
            if (type == Objects.requireNonNull(nfs).getType()) {
                return nfs;
            }
        }
        return null;
    }

    public void notifyAllUsers(){
        for (var user : userRepository.fetchAllUsers()) {
            getNotificationService(user.getNotificationType())
                    .notifyUser(user, "asdadfgdsfsd");
        }
    }
}
