package co.inventorsoft.academy.spring.service;

import co.inventorsoft.academy.spring.repository.CategoryRepository;
import co.inventorsoft.academy.spring.resolver.NotificationServiceResolver;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;

@Data
@AllArgsConstructor
@Service
public class ArticleAnalyzer {

    private ArticleService articleService;
    private CategoryRepository categoryRepository;
    private CategoryService categoryService;
    private NotificationServiceResolver notificationServiceResolver;

    public void articleAnalyze(){
        categoryRepository.saveCategories(
                categoryService.fetchCategories(
                        articleService.getSplittedArticleWords()));
        notificationServiceResolver.notifyAllUsers();
    }
}
