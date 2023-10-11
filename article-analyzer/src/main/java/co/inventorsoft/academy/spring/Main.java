package co.inventorsoft.academy.spring;

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
    public static void main(String[] args) throws IOException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(Main.class, args);

        ArticleService articleService = applicationContext.getBean(ArticleService.class);

        CategoriesService categoriesService = applicationContext.getBean(CategoriesService.class);

        categoriesService.createCategoriesFile(articleService.splitArticleWords());

        applicationContext.getBean(NotificationServiceResolver.class).notifyAllUsers();
    }
}