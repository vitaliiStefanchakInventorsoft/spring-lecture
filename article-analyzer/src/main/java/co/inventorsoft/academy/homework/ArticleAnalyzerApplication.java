package co.inventorsoft.academy.homework;

import co.inventorsoft.academy.homework.mapper.ArticleMapper;
import co.inventorsoft.academy.homework.mapper.UserMapper;
import co.inventorsoft.academy.homework.model.Article;
import co.inventorsoft.academy.homework.model.User;
import co.inventorsoft.academy.homework.nofifier.NotificationService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.File;
import java.util.List;
import java.util.Set;


@SpringBootApplication
public class ArticleAnalyzerApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(ArticleAnalyzerApplication.class);

        CategorySaver categorySaver = applicationContext.getBean(CategorySaver.class);
        ContentProcessor contentProcessor = applicationContext.getBean(ContentProcessor.class);

        ArticleMapper articleMapper = applicationContext.getBean(ArticleMapper.class);
        UserMapper userMapper = applicationContext.getBean(UserMapper.class);

        List<Article> articles = articleMapper.map(new File("articles.json"));
        Set<String> categories = contentProcessor.process(articles);

        List<User> users = userMapper.map(new File("users.json"));

        NotificationService notificationService = applicationContext.getBean(NotificationService.class);

        for (Article article : articles) {
            notificationService.notify(article);
        }
    }

}
