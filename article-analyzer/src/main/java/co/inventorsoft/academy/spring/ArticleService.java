package co.inventorsoft.academy.spring;

import co.inventorsoft.academy.spring.facory.NotificationServiceFactory;
import co.inventorsoft.academy.spring.model.User;
import co.inventorsoft.academy.spring.repository.CategoryRepository;
import co.inventorsoft.academy.spring.repository.UserRepository;
import co.inventorsoft.academy.spring.service.CategoryFileService;
import co.inventorsoft.academy.spring.service.CategoryService;
import co.inventorsoft.academy.spring.service.notifier.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.google.gson.Gson;

import java.util.List;
import java.util.Set;

@Service
public class ArticleService {
    private final CategoryRepository categoryRepository;
    private final CategoryService categoryService;
    private final CategoryFileService categoryFileService;
    private final NotificationServiceFactory notificationServiceFactory;
    private final UserRepository userRepository;
    @Autowired
    private Gson gson;

    @Autowired
    public ArticleService(CategoryRepository categoryRepository, CategoryService categoryService,
                          CategoryFileService categoryFileService, NotificationServiceFactory notificationServiceFactory,
                          UserRepository userRepository) {
        this.categoryRepository = categoryRepository;
        this.categoryService = categoryService;
        this.categoryFileService = categoryFileService;
        this.notificationServiceFactory = notificationServiceFactory;
        this.userRepository = userRepository;
    }

    public void processCategories() {
        Set<String> categories = categoryService.extractCategories();
        categoryFileService.saveCategories(categories);
    }

    public void pushNotifications() {
        System.out.println("\nStarting notification process...");
        Set<String> categories = categoryRepository.getCategoriesFromJson();
        List<User> users = userRepository.getUsersFromJson();

        String jsonCategories = gson.toJson(categories);

        for (User user : users) {
            NotificationService service = notificationServiceFactory.getService(user.getNotificationType());
            service.notifyUsers(user, jsonCategories);
        }
    }
}