package co.inventorsoft.academy.articleanalyzer.service;

import co.inventorsoft.academy.articleanalyzer.model.Article;
import co.inventorsoft.academy.articleanalyzer.model.User;
import co.inventorsoft.academy.articleanalyzer.repository.ArticleRepository;
import co.inventorsoft.academy.articleanalyzer.repository.CategoryRepository;
import co.inventorsoft.academy.articleanalyzer.repository.UserRepository;
import co.inventorsoft.academy.articleanalyzer.service.analyzer.AnalyzerService;
import co.inventorsoft.academy.articleanalyzer.service.notifier.NotificationService;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class ArticleService {

    private final UserRepository userRepository;
    private final ArticleRepository articleRepository;
    private final CategoryRepository categoryRepository;
    private final AnalyzerService analyzerService;
    private final ConfigurableApplicationContext applicationContext;

    public ArticleService(UserRepository userRepository,
                          ArticleRepository articleRepository,
                          CategoryRepository categoryRepository,
                          AnalyzerService analyzerService,
                          ConfigurableApplicationContext applicationContext) {

        this.userRepository = userRepository;
        this.articleRepository = articleRepository;
        this.categoryRepository = categoryRepository;
        this.analyzerService = analyzerService;
        this.applicationContext = applicationContext;
    }

    public void analyze() {
        Set<Article> articles = articleRepository.readAllArticles();
        List<String> categories = analyzerService.analyze(articles);
        saveCategoriesToJSONFileAsArray(categories);
        notifyUsers(categories);
    }

    public void saveCategoriesToJSONFileAsArray(List<String> categories) {
        categoryRepository.saveCategoriesToJSONFileAsArray(categories);
    }

    private void notifyUsers(List<String> categories) {
        Set<User> users = userRepository.readAllUsers();
        users.forEach(user -> {
            applicationContext.getBean(String.valueOf(user.getNotificationType()), NotificationService.class)
                    .notifyUsers(user, categories);
        });
    }
}
