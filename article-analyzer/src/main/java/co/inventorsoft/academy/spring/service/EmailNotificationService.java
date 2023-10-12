package co.inventorsoft.academy.spring.service;

import co.inventorsoft.academy.spring.model.NotificationType;
import co.inventorsoft.academy.spring.model.User;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

@Data
@NoArgsConstructor
@Service
public class EmailNotificationService extends NotificationService {

    private NotificationType type = NotificationType.EMAIL;

    @Override
    public void notifyUser(User user, String content) {
        System.out.printf("Sending email ---> Header: Hello user %s! The new article is here | Content: %s%n", user.getEmail(), content);
    }

}
