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

  private ArticleService articleService;
  private EmailNotificationService emailService;
  private SlackNotificationService slackService;
  private JsonUtil jsonUtil;

  @Autowired
  public ArticleAnalyzerService(ArticleService articleService, EmailNotificationService emailService,
      SlackNotificationService slackService, JsonUtil jsonUtil) {
    this.articleService = articleService;
    this.emailService = emailService;
    this.slackService = slackService;
    this.jsonUtil = jsonUtil;
  }

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
