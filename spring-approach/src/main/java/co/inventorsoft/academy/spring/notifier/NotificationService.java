package co.inventorsoft.academy.spring.notifier;

import co.inventorsoft.academy.spring.model.Article;
import co.inventorsoft.academy.spring.model.Subscriber;
import co.inventorsoft.academy.spring.repository.SubscriberRepository;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public abstract class NotificationService {

    @Autowired
    private SubscriberRepository subscriberRepository;
    private Gson gson;

    @Autowired
    public void setGson(Gson gson) {
        this.gson = gson;
    }

    public void notify(Article article) {
        System.out.println("Notifying about new article...");
        String jsonArticle = gson.toJson(article);
        List<Subscriber> subscribers = subscriberRepository.fetchAllSubscribers();
        subscribers.forEach(subscriber -> notifySubscriber(subscriber, jsonArticle));
    }

    protected abstract void notifySubscriber(Subscriber subscriber, String content);
}
