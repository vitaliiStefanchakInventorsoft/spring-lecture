package co.inventorsoft.academy.spring.repository;

import co.inventorsoft.academy.spring.model.Article;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor
@Repository
public class ArticleRepository {

    private final Gson gson;

    public List<Article> fetchAllArticles(){
        Type listType = new TypeToken<List<Article>>() {
        }.getType();
        try {
            return gson.fromJson(new FileReader("article-analyzer/src/main/resources/articles.json"), listType);
        } catch (FileNotFoundException e) {
            System.out.println(Arrays.toString(e.getStackTrace()));
            return null;
        }

    }
}
