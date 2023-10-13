package co.inventorsoft.academy.articleanalyzer.service;

import co.inventorsoft.academy.articleanalyzer.model.Article;
import co.inventorsoft.academy.articleanalyzer.model.User;
import co.inventorsoft.academy.articleanalyzer.repository.ArticleRepository;
import co.inventorsoft.academy.articleanalyzer.repository.CategoryRepository;
import co.inventorsoft.academy.articleanalyzer.repository.UserRepository;
import co.inventorsoft.academy.articleanalyzer.service.analyzer.AnalyzerService;
import co.inventorsoft.academy.articleanalyzer.service.notifier.NotificationService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@Getter
public class ArticleService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    ArticleRepository articleRepository;
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    AnalyzerService analyzerService;

    NotificationService notificationService;

    public void analyze() {
        Set<Article> articles = articleRepository.readAllArticles();
        analyzerService.analyze(articles);
    }

    public void saveCategoriesToJSONFileAsObject(String fileName) {
        categoryRepository.saveCategoriesToJSONFileAsObject(analyzerService.getCategories(), fileName);
    }

    public void saveCategoriesToJSONFileAsArray(String fileName) {
        categoryRepository.saveCategoriesToJSONFileAsArray(analyzerService.getCategories(), fileName);
    }

    public void notifyUsers(ConfigurableApplicationContext applicationContext) {
        Set<User> users = userRepository.readAllUsers();
        users.forEach(user -> {
            notificationService = applicationContext.getBean(String.valueOf(user.getNotificationType()), NotificationService.class);
            notificationService.notifyUsers(user, getCategories());
        });
    }

    public List<String> getCategories() {
        return analyzerService.getCategories();
    }
}
