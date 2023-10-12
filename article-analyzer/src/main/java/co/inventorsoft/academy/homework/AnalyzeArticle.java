package co.inventorsoft.academy.homework;

import co.inventorsoft.academy.homework.mapper.ArticleReader;
import co.inventorsoft.academy.homework.mapper.UserReader;
import co.inventorsoft.academy.homework.model.Article;
import co.inventorsoft.academy.homework.model.User;
import co.inventorsoft.academy.homework.nofifier.NotificationService;
import co.inventorsoft.academy.homework.service.impl.CategorySaverServiceImpl;
import co.inventorsoft.academy.homework.service.impl.ContentProcessorServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component
@AllArgsConstructor
public class AnalyzeArticle {
    private static String articlesFile;
    private static String usersFile;

    private final CategorySaverServiceImpl categorySaverServiceImpl;
    private final ContentProcessorServiceImpl contentProcessorServiceImpl;
    private final ArticleReader articleReader;
    private final UserReader userReader;
    private final List<NotificationService> notificationServiceList;

    @Value("${ARTICLES_FILE}")
    public void setArticlesFile(String articlesFile) {
        AnalyzeArticle.articlesFile = articlesFile;
    }

    @Value("${USERS_FILE}")
    public void setUsersFile(String usersFile) {
        AnalyzeArticle.usersFile = usersFile;
    }
    public void analyzeArticles() {
        List<Article> articleList = articleReader.jsonToListArticles(articlesFile);
        Set<String> categories = contentProcessorServiceImpl.process(articleList);
        categorySaverServiceImpl.saveCategory(categories);

        List<User> userList = userReader.jsonToListUsers(usersFile);

        for (User user : userList) {
            for (NotificationService notificationService : notificationServiceList) {
                if (notificationService.getSenderIdentifier().equals(user.getNotificationType())) {
                    for (Article article : articleList) {
                        notificationService.notify(user, article);
                    }
                }
            }
        }
    }
}
