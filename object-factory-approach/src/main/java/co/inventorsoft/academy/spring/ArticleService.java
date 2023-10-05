package co.inventorsoft.academy.spring;

import co.inventorsoft.academy.spring.model.Article;
import co.inventorsoft.academy.spring.notifier.NotificationService;
import co.inventorsoft.academy.spring.repository.ArticleRepository;
import co.inventorsoft.academy.spring.reviewer.ReviewService;

public class ArticleService {

    private final ArticleRepository articleRepository = ObjectFactory.getInstance().getObject(ArticleRepository.class);
    private final ReviewService reviewService = ObjectFactory.getInstance().getObject(ReviewService.class);
    private final NotificationService notificationService = ObjectFactory.getInstance().getObject(NotificationService.class);

    public void publishArticle(Article article) {
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
