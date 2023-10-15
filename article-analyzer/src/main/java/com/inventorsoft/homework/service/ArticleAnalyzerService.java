package com.inventorsoft.homework.service;

import com.inventorsoft.homework.entity.Article;
import com.inventorsoft.homework.entity.User;
import com.inventorsoft.homework.utility.JsonUtil;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArticleAnalyzerService {

  @Autowired
  private ArticleService articleService;

  @Autowired
  private EmailNotificationService emailService;

  @Autowired
  private SlackNotificationService slackService;

  @Autowired
  private JsonUtil jsonUtil;

  public void processArticlesAndNotify() {
    List<Article> articles = jsonUtil.readFromJson("articles.json", Article[].class);
    List<User> users = jsonUtil.readFromJson("users.json", User[].class);

    Set<String> categories = articleService.processArticleContent(articles);

    jsonUtil.saveToJson("categories.json", categories);

    for (User user : users) {
      switch (user.getNotificationType()) {
        case EMAIL:
          emailService.notifyUser(user, "Categories updated: " + categories);
          break;
        case SLACK:
          slackService.notifyUser(user, "Categories updated: " + categories);
          break;
      }
    }
  }
}
