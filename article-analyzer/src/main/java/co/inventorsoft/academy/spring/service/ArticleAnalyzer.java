package co.inventorsoft.academy.spring.service;

import co.inventorsoft.academy.spring.resolver.NotificationServiceResolver;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;

@Data
@AllArgsConstructor
@Service
public class ArticleAnalyzer {

    private ArticleService articleService;
    private CategoriesService categoriesService;
    private NotificationServiceResolver resolver;

    public void articleAnalyze(){
        categoriesService.createCategoriesFile(articleService.splitArticleWords());
        resolver.notifyAllUsers();
    }
}
