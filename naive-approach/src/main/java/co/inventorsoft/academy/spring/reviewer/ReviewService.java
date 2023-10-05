package co.inventorsoft.academy.spring.reviewer;

import co.inventorsoft.academy.spring.model.Article;

import java.util.Random;

public class ReviewService {

    private final ReviewType reviewType;

    public ReviewService(ReviewType reviewType) {
        this.reviewType = reviewType;
    }

    public boolean provideReview(Article article) {
        System.out.printf("Providing %s review for new article (%s)...%n", reviewType, article.getName());
        return new Random().nextBoolean();
    }
}
