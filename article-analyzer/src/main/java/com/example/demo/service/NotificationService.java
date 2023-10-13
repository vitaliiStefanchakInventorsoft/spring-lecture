package com.example.demo.service;

import com.example.demo.entities.Article;
import com.example.demo.entities.User;
import com.example.demo.files.JsonFileReader;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@Slf4j
public abstract class NotificationService {

    private List<User> subscribers;

    @Autowired
    private JsonFileReader jsonFileReader;

    private Gson gson;

    @Autowired
    public void setGson(Gson gson) {
        this.gson = gson;
    }

    public void nofity(Set<String> uniqueCategories) {
        subscribers = jsonFileReader.readUsersFromJsonFile("C:\\Users\\User\\IdeaProjects\\InventorSoft_Homework_3\\article-analyzer\\users.json");
        subscribers.forEach(subscriber -> notifySubscriber(subscriber, uniqueCategories));
    }

    protected abstract void notifySubscriber(User subscriber, Set<String> uniqueCategories);

}
