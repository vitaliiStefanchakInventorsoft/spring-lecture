package co.inventorsoft.academy.spring.services;

import co.inventorsoft.academy.spring.models.NotificationType;
import co.inventorsoft.academy.spring.models.User;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



/**
 * Notification by slack service.
 */
@Service
public class NotificationSlackService implements NotificationService {
    private final UserService userService;
    @Autowired
    public NotificationSlackService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean notifyUser(UUID userId, String notification){
        User currentUser = this.userService.getUserById(userId).orElseGet(() -> User.builder()
            .email("userNotFound@mail.com")
            .username("userNotFound")
            .slackId("userNotFound")
            .build()
        );

        if(currentUser.getNotificationType().equals(NotificationType.SLACK)){
            System.out.println("Dear " + currentUser.getUsername() + ", you have new notification:\n" + notification);
        }else {
            throw new UnsupportedOperationException("Wrong notification type");
        }

        return !currentUser.getEmail().equals("userNotFound@mail.com");
    }
}
