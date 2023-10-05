package co.inventorsoft.academy.spring;

import co.inventorsoft.academy.spring.model.Article;
import co.inventorsoft.academy.spring.notifier.NotificationService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(Main.class, args);

        ArticleService articleService = applicationContext.getBean(ArticleService.class);

        articleService.publishArticle(new Article(1L, "Spring lecture is here"));
    }
}