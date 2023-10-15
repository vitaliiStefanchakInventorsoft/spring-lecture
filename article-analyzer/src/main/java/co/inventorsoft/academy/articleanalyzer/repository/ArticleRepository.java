package co.inventorsoft.academy.articleanalyzer.repository;

import co.inventorsoft.academy.articleanalyzer.model.Article;
import co.inventorsoft.academy.articleanalyzer.service.jsonmanager.JsonManagerService;
import com.google.gson.reflect.TypeToken;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Type;
import java.util.Set;

@Repository
@AllArgsConstructor
public class ArticleRepository {

    private final JsonManagerService<Article> jsonManagerService;
    private final String articlesPath;

    public Set<Article> readAllArticles() {
        Type typeToken = new TypeToken<Set<Article>>() {
        }.getType();
        return jsonManagerService.readAll(articlesPath, typeToken);
    }
}
