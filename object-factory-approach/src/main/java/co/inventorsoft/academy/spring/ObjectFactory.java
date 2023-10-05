package co.inventorsoft.academy.spring;

import co.inventorsoft.academy.spring.notifier.EmailNotificationService;
import co.inventorsoft.academy.spring.notifier.NotificationService;
import co.inventorsoft.academy.spring.repository.ArticleRepository;
import co.inventorsoft.academy.spring.repository.SubscriberRepository;
import co.inventorsoft.academy.spring.reviewer.ReviewService;
import co.inventorsoft.academy.spring.reviewer.ReviewType;
import com.google.gson.Gson;

public class ObjectFactory {

    private static final ObjectFactory instance = new ObjectFactory();

    private ObjectFactory() {
    }

    public static ObjectFactory getInstance() {
        return instance;
    }

    public <T> T getObject(Class<T> clazz) {
        if (clazz.equals(NotificationService.class)) {
            return (T) new EmailNotificationService();
        } else if (clazz.equals(ArticleRepository.class)) {
            return (T) new ArticleRepository();
        } else if (clazz.equals(SubscriberRepository.class)) {
            return (T) new SubscriberRepository();
        } else if (clazz.equals(ReviewService.class)) {
            return (T) new ReviewService(ReviewType.FULL);
        } else if (clazz.equals(Gson.class)) {
            return (T) new Gson();
        } else if (clazz.equals(ArticleService.class)) {
            return (T) new ArticleService();
        }

        return null;
    }

}
