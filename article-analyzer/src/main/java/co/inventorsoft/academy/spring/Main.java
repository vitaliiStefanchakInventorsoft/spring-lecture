package co.inventorsoft.academy.spring;

import co.inventorsoft.academy.spring.service.ArticleAnalyzer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class Main {
    public static void main(String[] args){
        ConfigurableApplicationContext applicationContext = SpringApplication.run(Main.class, args);

        ArticleAnalyzer articleAnalyzer = applicationContext.getBean(ArticleAnalyzer.class);

        articleAnalyzer.articleAnalyze();
    }
}