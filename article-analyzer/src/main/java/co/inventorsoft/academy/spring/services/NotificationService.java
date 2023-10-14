package co.inventorsoft.academy.spring.services;

import java.util.UUID;
import org.springframework.stereotype.Service;


/**
 * Notification service.
 */
@Service
public interface NotificationService {

    boolean notifyUser(UUID userId, String notification);
}
