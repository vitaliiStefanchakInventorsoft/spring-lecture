package co.inventorsoft.academy.spring.service;

import co.inventorsoft.academy.spring.model.NotificationType;
import co.inventorsoft.academy.spring.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Service
public abstract class NotificationService {

    private NotificationType type = null;

    public abstract void notifyUser(User user, String content);
}
