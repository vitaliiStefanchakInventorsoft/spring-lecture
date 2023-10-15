package co.inventorsoft.academy.spring.services;

import org.springframework.stereotype.Service;


/**
 * Notification service interface to store common used operations.
 */
@Service
public interface NotificationService {

    void notifyUser(Long userId, String notification);
}
