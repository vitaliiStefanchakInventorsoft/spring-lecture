package com.example.demo.service;

import com.example.demo.entities.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@Slf4j
public class SlackNotificationService extends NotificationService{

    @Override
    protected void notifySubscriber(User subscriber, Set<String> uniqueCategories) {
        if (subscriber.getSlackId() != null) {
            log.info("Sending slack message to user {}. Content: {}", subscriber.getUsername(), uniqueCategories);
        }
    }
}
