package co.inventorsoft.academy.spring.repository;

import co.inventorsoft.academy.spring.model.Subscriber;

import java.util.List;

public class SubscriberRepository {

    public List<Subscriber> fetchAllSubscribers() {
        return List.of(new Subscriber(1L, "sub1@mail.com"), new Subscriber(2L, "sub2@mail.com"));
    }
}
