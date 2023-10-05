package co.inventorsoft.academy.spring.reviewer;

import co.inventorsoft.academy.spring.model.Article;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class ReviewService {

    @Value("${review.type}")
    private String reviewType;

    public boolean provideReview(Article article) {
        System.out.printf("Providing %s review for new article (%s)...%n", reviewType, article.getName());
        return new Random().nextBoolean();
    }
}
