package co.inventorsoft.academy.homework.nofifier;

import co.inventorsoft.academy.homework.model.Article;
import co.inventorsoft.academy.homework.model.User;


public interface NotificationService {

    void notify(User user, Article article);

    String getSenderIdentifier();

}
