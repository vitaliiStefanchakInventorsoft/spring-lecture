package com.articleanalyzer.core.services;

import com.articleanalyzer.core.models.User;
import org.springframework.stereotype.Service;

@Service
public abstract class NotificationService {
    public abstract void notifyUser(User user);
}
