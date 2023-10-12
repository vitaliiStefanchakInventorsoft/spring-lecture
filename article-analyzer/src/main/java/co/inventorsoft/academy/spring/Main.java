package co.inventorsoft.academy.spring;

import co.inventorsoft.academy.spring.service.ArticleAnalyzer;
import co.inventorsoft.academy.spring.service.ArticleService;
import co.inventorsoft.academy.spring.service.CategoriesService;
import co.inventorsoft.academy.spring.resolver.NotificationServiceResolver;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

@SpringBootApplication
public class Main {
    public static void main(String[] args){
        ConfigurableApplicationContext applicationContext = SpringApplication.run(Main.class, args);

        ArticleAnalyzer articleAnalyzer = applicationContext.getBean(ArticleAnalyzer.class);

        articleAnalyzer.articleAnalyze();
    }
}