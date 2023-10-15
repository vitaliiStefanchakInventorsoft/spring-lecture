package com.example.demo.service;

import com.example.demo.entities.Article;
import com.example.demo.entities.User;
import com.example.demo.factories.NotificationServiceFactory;
import com.example.demo.files.JsonFileReader;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class ArticleService {

    private Set<String> helperWords;

    private CategoryService categoryService;

    private JsonFileReader jsonFileReader;

    private EmailNotificationService emailNotificationService;

    private SlackNotificationService slackNotificationService;

    private NotificationServiceFactory notificationServiceFactory;

    public ArticleService(Set<String> helperWords, CategoryService categoryService, JsonFileReader jsonFileReader, EmailNotificationService emailNotificationService, SlackNotificationService slackNotificationService, NotificationServiceFactory notificationServiceFactory) {
        this.helperWords = helperWords;
        this.categoryService = categoryService;
        this.jsonFileReader = jsonFileReader;
        this.emailNotificationService = emailNotificationService;
        this.slackNotificationService = slackNotificationService;
        this.notificationServiceFactory = notificationServiceFactory;
    }

    public void processArticles() {
        List<Article> articles = jsonFileReader.readArticlesFromJsonFile("article-analyzer/articles.json");
        Set<String> uniqueCategories = new HashSet<>();

        for (Article article : articles) {
            String content = article.getContent().toLowerCase();
            String[] words = content.split("\\s+");

            Map<String, Integer> wordCount = new HashMap<>();

            for (String word : words) {
                word = word.replaceAll("[^a-zA-Z]", "");
                if (!helperWords.contains(word) && word.length() > 1) {
                    wordCount.compute(word, (k, v) -> v == null ? 1 : v + 1);
                }
            }
            System.out.println(wordCount);

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
    }

    public void notifyUsers() {
        List<User> users = jsonFileReader.readUsersFromJsonFile("article-analyzer/users.json");
        Set<String> uniqueCategories = jsonFileReader.readCategoriesFromFile("article-analyzer/src/main/resources/categories.json");
        users.stream().forEach(user -> {
            notificationServiceFactory.createNotificationService(user.getNotificationType()).nofity(uniqueCategories, user);
        });
    }
}
