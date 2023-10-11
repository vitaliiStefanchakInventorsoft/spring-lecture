package co.inventorsoft.academy.spring.repository;

import co.inventorsoft.academy.spring.model.Article;
import co.inventorsoft.academy.spring.model.User;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.List;

@RequiredArgsConstructor
@Repository
public class ArticleRepository {

    private final Gson gson;

    public List<Article> fetchAllArticles() throws FileNotFoundException {
        Type listType = new TypeToken<List<Article>>() {}.getType();
        return gson.fromJson(new FileReader("article-analyzer/src/main/resources/articles.json"), listType);

    }
}
