package co.inventorsoft.academy.spring.services;

import co.inventorsoft.academy.spring.models.Article;
import co.inventorsoft.academy.spring.repositories.ArticleRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * Article service.
 */
@Service
public class ArticleService {
    private final ArticleRepository articleRepository;

    @Autowired
    public ArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public List<Article> getAllArticles(){
        return articleRepository.getArticles();
    }
}
