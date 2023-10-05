package co.inventorsoft.academy.spring;

import co.inventorsoft.academy.spring.model.Article;
import co.inventorsoft.academy.spring.notifier.NotificationService;
import co.inventorsoft.academy.spring.repository.ArticleRepository;
import co.inventorsoft.academy.spring.reviewer.ReviewService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@Service
public class ArticleService {

    private final ArticleRepository articleRepository;
    private final ReviewService reviewService;
    private final NotificationService notificationService;
    private final Environment environment;

    @Value("${env.specific.prop}")
    private String envSpecificProperty;

    public ArticleService(ArticleRepository articleRepository,
                          ReviewService reviewService,
                          NotificationService notificationService,
                          Environment environment) {
        this.articleRepository = articleRepository;
        this.reviewService = reviewService;
        this.notificationService = notificationService;
        this.environment = environment;
    }

    public void publishArticle(Article article) {
        System.out.printf("Loading property %s from %s environment...%n", envSpecificProperty, environment.getActiveProfiles()[0]);
        System.out.println("Starting publishing process...");

        articleRepository.save(article);
        boolean reviewResult = reviewService.provideReview(article);
        if (reviewResult) {
            notificationService.notify(article);
        } else {
           throw new RuntimeException("Publishing process is interrupted: Review failed!");
        }

        System.out.println("Publishing process is finished...");
    }
}
