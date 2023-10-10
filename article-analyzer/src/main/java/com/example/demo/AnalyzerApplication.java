package com.example.demo;

import com.example.demo.service.ArticleAnalyzerService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AnalyzerApplication {
  @Autowired
  private ArticleAnalyzerService articleAnalyzerService;

  public static void main(String[] args) {
    SpringApplication.run(AnalyzerApplication.class, args);
  }

  @PostConstruct
  public void analyzeArticles() {
    articleAnalyzerService.processArticleFile("articles.json", "users.json");
  }
}
