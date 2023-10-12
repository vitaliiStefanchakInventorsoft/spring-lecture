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
        List<Article> articleList = articleReader.jsonToListArticles(ARTICLES_FILE);
        Set<String> categories = contentProcessorServiceImpl.process(articleList);
        categorySaverServiceImpl.saveCategory(categories);

        List<User> userList = userReader.jsonToListUsers(USERS_FILE);

        for (User user : userList) {
            for (NotificationService notificationService : notificationServiceList) {
                if (notificationService.getSenderIdentifier().equals(user.getNotificationType())){
                    for (Article article : articleList) {
                        notificationService.notify(user, article);
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        SpringApplication.run(ArticleAnalyzerApplication.class);
    }
}
