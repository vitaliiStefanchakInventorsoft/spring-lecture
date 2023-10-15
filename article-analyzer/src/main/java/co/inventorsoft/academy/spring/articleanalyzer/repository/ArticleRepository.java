package co.inventorsoft.academy.spring.articleanalyzer.repository;

import co.inventorsoft.academy.spring.articleanalyzer.model.Article;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

@Repository("articleRepository")
public class ArticleRepository {
    private final Gson gson;
    private final String filePath;
    private static final Type ARTICLE_LIST_TYPE = new TypeToken<List<Article>>(){}.getType();


    ArticleRepository(Gson gson, @Value("${read.article.file.path}") String filePath) {
        this.gson = gson;
        this.filePath = filePath;
    }

    // Use the default file path
    public List<Article> fetchAllArticles() {
        return fetchAllArticles(this.filePath);
    }

    // Read all articles from the JSON file
    public List<Article> fetchAllArticles(String filePath) {
        try {
            // Deserialize the JSON data into an array of Article objects
            JsonReader reader = new JsonReader(new FileReader(filePath));
            List<Article> articles = gson.fromJson(reader, ARTICLE_LIST_TYPE);
            reader.close();
            return articles;
        } catch (IOException e) {
            e.printStackTrace();
            // return empty list if exception occurs
            return List.of();
        }
    }
}