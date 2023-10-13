package com.example.demo.service;

import com.example.demo.entities.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@Slf4j
public class EmailNotificationService extends NotificationService {

    @Override
    protected void notifySubscriber(User subscriber, Set<String> uniqueCategories) {
        if (subscriber.getEmail() != null) {
            log.info("Sending email to user {}. Content: {}", subscriber.getUsername(), uniqueCategories);
        }
    }
}
