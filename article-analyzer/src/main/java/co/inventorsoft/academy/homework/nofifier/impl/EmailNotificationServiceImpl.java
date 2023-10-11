package co.inventorsoft.academy.homework.nofifier.impl;

import co.inventorsoft.academy.homework.model.Article;
import co.inventorsoft.academy.homework.model.User;
import co.inventorsoft.academy.homework.nofifier.NotificationService;
import org.springframework.stereotype.Service;

@Service
public class EmailNotificationServiceImpl implements NotificationService {

    @Override
    public void notify(User user, Article article) {
        System.out.printf("Sending email notification about article with name %s to user %s...\n", article.getName(), user.getUsername());
    }

    @Override
    public String getSenderIdentifier() {
        return "EMAIL";
    }
}
