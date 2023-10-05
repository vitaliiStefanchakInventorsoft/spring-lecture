package co.inventorsoft.academy.spring;

import co.inventorsoft.academy.spring.model.Article;

public class Main {
    public static void main(String[] args) {
        ArticleService articleService = new ArticleService();
        articleService.publishArticle(new Article(1L, "Spring lecture is here"));
    }
}