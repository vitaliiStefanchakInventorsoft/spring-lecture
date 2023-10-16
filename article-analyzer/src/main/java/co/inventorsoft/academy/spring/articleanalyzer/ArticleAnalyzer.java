package co.inventorsoft.academy.spring.articleanalyzer;

import co.inventorsoft.academy.spring.articleanalyzer.model.Article;
import co.inventorsoft.academy.spring.articleanalyzer.notifier.NotificationService;
import co.inventorsoft.academy.spring.articleanalyzer.repository.ArticleRepository;
import co.inventorsoft.academy.spring.articleanalyzer.service.CategoryService;
import co.inventorsoft.academy.spring.articleanalyzer.service.SetToJsonWriter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Set;

@Service
public class ArticleAnalyzer {
    ArticleRepository articleRepository;
    CategoryService categoryService;
    SetToJsonWriter setToJsonWriter;
    NotificationService notificationService;

    public ArticleAnalyzer(ArticleRepository articleRepository,
                           CategoryService categoryService,
                           SetToJsonWriter setToJsonWriter,
                           @Qualifier("emailNotificationService") NotificationService notificationService) {
        this.articleRepository = articleRepository;
        this.categoryService = categoryService;
        this.setToJsonWriter = setToJsonWriter;
        this.notificationService = notificationService;
    }

    public void analyze() {
        List<Article> articles = articleRepository.fetchAllArticles();
        Set<String> categories = categoryService.findMostCommonCategories(articles);
        setToJsonWriter.write(categories);
        notificationService.notify(categoryService.findMostCommonCategories(articles));
    }
}
