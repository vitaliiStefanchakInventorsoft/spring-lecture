package com.example.demo.service;

import com.example.demo.entities.Article;
import com.example.demo.entities.User;
import com.example.demo.enums.NotificationType;
import com.example.demo.files.JsonFileReader;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class ArticleService {

    @Autowired
    private Set<String> helperWords;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private JsonFileReader jsonFileReader;

//    @Autowired
//    private NotificationService notificationService;

    @Autowired
    private EmailNotificationService emailNotificationService;

    @Autowired SlackNotificationService slackNotificationService;

    public void processArticles() {
        List<Article> articles = jsonFileReader.readArticlesFromJsonFile("C:\\Users\\User\\IdeaProjects\\InventorSoft_Homework_3\\article-analyzer\\articles.json");
        Set<String> uniqueCategories = new HashSet<>();

        for (Article article : articles) {
            String content = article.getContent().toLowerCase();
            String[] words = content.split(" ");

            Map<String, Integer> wordCount = new HashMap<>();

            for (String word : words) {
                if (!helperWords.contains(word) && word.length() > 1) {
                    wordCount.put(word, wordCount.getOrDefault(word, 0) + 1);
                }
            }

            if (!wordCount.isEmpty()) {
                int maxCount = Collections.max(wordCount.values());

                for (Map.Entry<String, Integer> entry : wordCount.entrySet()) {
                    if (entry.getValue() == maxCount) {
                        uniqueCategories.add(entry.getKey());
                    }
                }
            }
        }

        categoryService.saveUniqueCategories(uniqueCategories);
        List<User> users = jsonFileReader.readUsersFromJsonFile("C:\\Users\\User\\IdeaProjects\\InventorSoft_Homework_3\\article-analyzer\\users.json");
        for (User user : users) {
            if (user.getNotificationType() == NotificationType.EMAIL) {
                emailNotificationService.notifySubscriber(user, uniqueCategories);
            } else if (user.getNotificationType() == NotificationType.SLACK) {
                slackNotificationService.notifySubscriber(user, uniqueCategories);
            }
        }
    }

    public void notificateUsersAboutResult() {

    }


}
