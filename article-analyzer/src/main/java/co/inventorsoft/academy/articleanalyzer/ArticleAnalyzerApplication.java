package co.inventorsoft.academy.articleanalyzer;

import co.inventorsoft.academy.articleanalyzer.service.ArticleService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class ArticleAnalyzerApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(ArticleAnalyzerApplication.class, args);

        ArticleService articleService = applicationContext.getBean(ArticleService.class);

        articleService.analyze();
        articleService.saveCategoriesToJSONFileAsArray("categories");
        articleService.notifyUsers(applicationContext);

    }

}
