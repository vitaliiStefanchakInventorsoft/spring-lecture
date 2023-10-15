package co.inventorsoft.academy.spring.repository;

import co.inventorsoft.academy.spring.model.Article;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import lombok.AllArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Repository;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Repository
@AllArgsConstructor
public class ArticleRepository {
    private Gson gson;

    public List<Article> getArticles() {
        ClassPathResource resource = new ClassPathResource("articles.json");
        try(InputStreamReader reader = new InputStreamReader(resource.getInputStream())) {
            return Arrays.asList(gson.fromJson(reader, Article[].class));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
