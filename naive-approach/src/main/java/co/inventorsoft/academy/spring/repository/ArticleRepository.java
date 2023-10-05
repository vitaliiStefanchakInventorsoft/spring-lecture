package co.inventorsoft.academy.spring.repository;

import co.inventorsoft.academy.spring.model.Article;

public class ArticleRepository {

    public void save(Article article) {
        System.out.println("Saving new article...");
    }
}
