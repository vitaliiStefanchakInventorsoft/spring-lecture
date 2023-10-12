package co.inventorsoft.academy.spring.service;

import co.inventorsoft.academy.spring.repository.CategoryRepository;
import co.inventorsoft.academy.spring.resolver.NotificationServiceResolver;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@AllArgsConstructor
@Service
public class ArticleAnalyzer {

    private ArticleService articleService;
    private CategoryRepository categoryRepository;
    private CategoryService categoryService;
    private NotificationServiceResolver notificationServiceResolver;

    public void articleAnalyze(){
        List<List<String>> splittedWords = articleService.getSplittedArticleWords();

        Set<String> categories = categoryService.fetchCategories(splittedWords);

        categoryRepository.saveCategories(categories);

        notificationServiceResolver.notifyAllUsers();
    }
}
