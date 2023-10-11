package co.inventorsoft.academy.homework;

import co.inventorsoft.academy.homework.mapper.ArticleReader;
import co.inventorsoft.academy.homework.mapper.UserReader;
import co.inventorsoft.academy.homework.model.Article;
import co.inventorsoft.academy.homework.model.User;
import co.inventorsoft.academy.homework.nofifier.NotificationService;
import co.inventorsoft.academy.homework.service.impl.CategorySaverServiceImpl;
import co.inventorsoft.academy.homework.service.impl.ContentProcessorServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;

import java.io.File;
import java.util.List;
import java.util.Set;


@SpringBootApplication
@AllArgsConstructor
public class ArticleAnalyzerApplication {
    private static final String ARTICLES_FILE = "articles.json";
    private static final String USERS_FILE = "users.json";

    private final CategorySaverServiceImpl categorySaverServiceImpl;
    private final ContentProcessorServiceImpl contentProcessorServiceImpl;
    private final ArticleReader articleReader;
    private final UserReader userReader;
    private final List<NotificationService> notificationServiceList;

    @EventListener(ContextRefreshedEvent.class)
    public void runOnStartUp() {
        List<Article> articleList = articleReader.jsonToListArticles(new File(ARTICLES_FILE));
        Set<String> categories = contentProcessorServiceImpl.process(articleList);

        List<User> userList = userReader.jsonToListUsers(new File(USERS_FILE));

        userList.forEach(user -> notificationServiceList.stream()
                .filter(service -> service.getSenderIdentifier().equals(user.getNotificationType()))
                .forEach(service -> articleList.forEach(article -> service.notify(user, article))));
    }

    public static void main(String[] args) {
        SpringApplication.run(ArticleAnalyzerApplication.class);
    }
}
