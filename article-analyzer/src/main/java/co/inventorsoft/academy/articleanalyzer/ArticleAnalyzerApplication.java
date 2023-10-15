package co.inventorsoft.academy.articleanalyzer;

import co.inventorsoft.academy.articleanalyzer.service.ArticleService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class ArticleAnalyzerApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(ArticleAnalyzerApplication.class, args);
        applicationContext.getBean(ArticleService.class).analyze();
    }

}
