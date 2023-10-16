package co.inventorsoft.academy.spring.repository;

import co.inventorsoft.academy.spring.model.Article;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Repository
public class ArticleRepository {
    @Autowired
    private Gson gson;
    public List<Article> getArticlesFromJson() {
        InputStream is = getClass().getResourceAsStream("/articles.json");

        Optional<InputStream> isOpt = Optional.ofNullable(is);
        if (isOpt.isEmpty()) {
            return Collections.emptyList();
        }

        Type articlesListType = new TypeToken<ArrayList<Article>>() {}.getType();

        return gson.fromJson(new InputStreamReader(isOpt.get()), articlesListType);
    }
}
