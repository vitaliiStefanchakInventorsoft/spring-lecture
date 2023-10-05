package co.inventorsoft.academy.spring.notifier;

import co.inventorsoft.academy.spring.model.Article;
import co.inventorsoft.academy.spring.model.Subscriber;
import co.inventorsoft.academy.spring.repository.SubscriberRepository;
import com.google.gson.Gson;

import java.util.List;

public abstract class NotificationService {

    private final SubscriberRepository subscriberRepository = new SubscriberRepository();
    private final Gson gson = new Gson();

    public void notify(Article article) {
        System.out.println("Notifying about new article...");
        String jsonArticle = gson.toJson(article);
        List<Subscriber> subscribers = subscriberRepository.fetchAllSubscribers();
        subscribers.forEach(subscriber -> notifySubscriber(subscriber, jsonArticle));
    }

    protected abstract void notifySubscriber(Subscriber subscriber, String content);
}
