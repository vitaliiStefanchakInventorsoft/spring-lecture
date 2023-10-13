package co.inventorsoft.academy.spring.service;

import co.inventorsoft.academy.spring.notifier.NotificationManager;
import co.inventorsoft.academy.spring.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ArticleAnalyzerService {
    private final ArticleService articleService;
    private final CategoryRepository categoryRepository;
    private final CategoryService categoryService;
    private final NotificationManager notificationManager;

    public void analyze(){
        List<List<String>> splitWords = articleService.getSplitArticleWords();

        categoryRepository.saveCategories(categoryService.getCategories(splitWords));
        notificationManager.sendNotifications();
    }
}
