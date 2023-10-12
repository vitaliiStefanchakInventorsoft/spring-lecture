package co.inventorsoft.academy.spring.service;

import co.inventorsoft.academy.spring.model.NotificationType;
import co.inventorsoft.academy.spring.model.User;
import co.inventorsoft.academy.spring.repository.UserRepository;
import co.inventorsoft.academy.spring.resolver.NotificationServiceResolver;
import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Service
public abstract class NotificationService {


    private UserRepository userRepository;

    private Gson gson;

    private NotificationType type = null;

    private NotificationServiceResolver resolver;

    public abstract void notifyUser(User user, String content);
}
