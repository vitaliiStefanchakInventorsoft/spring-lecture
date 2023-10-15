package com.example.demo.service;

import com.example.demo.entities.User;
import com.example.demo.files.JsonFileReader;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

    @Service
    @Slf4j
    public abstract class NotificationService {

        private JsonFileReader jsonFileReader;

        private Gson gson;

        @Autowired
        public void setGson(Gson gson) {
            this.gson = gson;
        }

        public NotificationService(JsonFileReader jsonFileReader) {
            this.jsonFileReader = jsonFileReader;
        }

        public void nofity(Set<String> uniqueCategories, User subscriber) {
            notifySubscriber(subscriber, uniqueCategories);
        }

        protected abstract void notifySubscriber(User subscriber, Set<String> uniqueCategories);

    }
