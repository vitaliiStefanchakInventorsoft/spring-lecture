package com.example.demo.service;

import com.example.demo.model.Article;
import com.example.demo.model.Category;
import com.example.demo.model.Message;
import com.example.demo.model.User;
import com.example.demo.service.notifications.NotificationService;
import com.example.demo.util.FileHandler;
import com.example.demo.util.JsonParserUtil;
import com.example.demo.util.NotificationServiceFactory;
import com.google.gson.JsonArray;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ArticleAnalyzerService {
  private final List<String> helperWords;
  private final FileHandler fileHandler;
  private final String categoriesFilePath;
  private final JsonParserUtil jsonParserUtil;
  private final NotificationServiceFactory notificationServiceFactory;


  public ArticleAnalyzerService(@Value("${helper_words}") List<String> helperWordsList,
                                @Value("${categories_file_path}") String categoriesFilePath,
                                FileHandler fileHandler,
                                JsonParserUtil jsonParserUtil, NotificationServiceFactory notificationServiceFactory) {
    this.helperWords = helperWordsList;
    this.fileHandler = fileHandler;
    this.categoriesFilePath = categoriesFilePath;
    this.jsonParserUtil = jsonParserUtil;
    this.notificationServiceFactory = notificationServiceFactory;
  }

  public void processArticleFile(String filePath, String usersFilePath) {
    JsonArray jsonArray = fileHandler.getDataFromJsonFile(filePath);
    Article[] articles = jsonParserUtil.parseJsonArray(jsonArray,Article[].class);
    Category category = getCategories(articles);
    fileHandler.saveDataToFile(category, categoriesFilePath);
    sendNotifications(usersFilePath);
  }

  private void sendNotifications(String usersFilePath) {
    JsonArray jsonArray = fileHandler.getDataFromJsonFile(usersFilePath);
    User[] users = jsonParserUtil.parseJsonArray(jsonArray,User[].class);
    for (User user : users) {
      NotificationService notificationService =
          notificationServiceFactory.getNotificationService(user.getNotificationType());
      Message message = new Message("categories was analyzed and saved", user);
      notificationService.sendNotification(message);
    }
  }
  private Category getCategories(Article[] articles) {
    return Category.builder()
        .categoryList(Arrays.stream(articles)
            .flatMap(article -> Arrays.stream(article.getContent().toLowerCase().replace("\n", "").split(" ")))
            .filter(s -> !helperWords.contains(s))
            .collect(Collectors.toMap(s -> s, s -> 1, Integer::sum))
            .entrySet()
            .stream()
            .filter(entry -> entry.getValue() >= 2)
            .map(Map.Entry::getKey)
            .collect(Collectors.toList()))
        .build();
  }
}
