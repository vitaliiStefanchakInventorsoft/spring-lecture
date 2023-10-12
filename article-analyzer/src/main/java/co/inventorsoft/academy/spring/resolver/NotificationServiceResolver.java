package co.inventorsoft.academy.spring.resolver;

import co.inventorsoft.academy.spring.model.NotificationType;
import co.inventorsoft.academy.spring.repository.UserRepository;
import co.inventorsoft.academy.spring.service.EmailNotificationService;
import co.inventorsoft.academy.spring.service.NotificationService;
import co.inventorsoft.academy.spring.service.SlackNotificationService;
import lombok.AllArgsConstructor;
import lombok.Data;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Getter
@AllArgsConstructor
@Component
public class NotificationServiceResolver {

    private EmailNotificationService emailNF;
    private SlackNotificationService slackNF;
    private UserRepository userRepository;

    public NotificationService getNotificationService(NotificationType type) {
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
    }

    public void notifyAllUsers() {
        for (var user : userRepository.fetchAllUsers()) {
            getNotificationService(user.getNotificationType())
                    .notifyUser(user, "asdadfgdsfsd");
        }
    }
}
