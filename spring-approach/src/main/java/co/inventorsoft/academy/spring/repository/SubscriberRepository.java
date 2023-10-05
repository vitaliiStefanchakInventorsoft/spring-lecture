package co.inventorsoft.academy.spring.repository;

import co.inventorsoft.academy.spring.model.Subscriber;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SubscriberRepository {

    public List<Subscriber> fetchAllSubscribers() {
        return List.of(new Subscriber(1L, "sub1@mail.com"), new Subscriber(2L, "sub2@mail.com"));
    }
}
