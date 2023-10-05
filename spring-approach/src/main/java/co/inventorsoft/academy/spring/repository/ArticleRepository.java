package co.inventorsoft.academy.spring.repository;

import co.inventorsoft.academy.spring.model.Article;
import org.springframework.stereotype.Repository;

@Repository
public class ArticleRepository {

    public void save(Article article) {
        System.out.println("Saving new article...");
    }
}
