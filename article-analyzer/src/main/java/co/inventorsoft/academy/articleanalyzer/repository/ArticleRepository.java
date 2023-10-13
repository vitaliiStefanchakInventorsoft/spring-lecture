package co.inventorsoft.academy.articleanalyzer.repository;

import co.inventorsoft.academy.articleanalyzer.model.Article;
import co.inventorsoft.academy.articleanalyzer.service.jsonmanager.JsonManagerService;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Type;
import java.util.Set;

@Repository
public class ArticleRepository {
    @Autowired
    JsonManagerService<Article> jsonManagerService;
    @Value("${articles.path}")
    private String usersPath;

    public Set<Article> readAllArticles() {
        Type typeToken = new TypeToken<Set<Article>>() {
        }.getType();
        return jsonManagerService.readAll(usersPath, typeToken);
    }
}
