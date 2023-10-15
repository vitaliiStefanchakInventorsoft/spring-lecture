package co.inventorsoft.academy.spring.Service;

import co.inventorsoft.academy.spring.notifier.NotificationManager;
import co.inventorsoft.academy.spring.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ArticleAnalyzerService {
    private final ArticleService articleService;
    private final CategoryService categoryService;
    private final CategoryRepository categoryRepository;
    private final NotificationManager notificationManager;

    public void analyze() {
        List<List<String>> words = articleService.getArticleWords();
        categoryRepository.saveCategories(categoryService.getCategories(words));
        notificationManager.sendNotification();
    }
}
