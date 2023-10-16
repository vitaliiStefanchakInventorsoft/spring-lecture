package co.inventorsoft.academy.spring.articleanalyzer.repository;

import co.inventorsoft.academy.spring.articleanalyzer.model.Article;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

@Repository
public class ArticleRepository {
    private Gson gson;

    @Value("${read.article.file.path}")
    private String filePath;

    private static final Type ARTICLE_LIST_TYPE = new TypeToken<List<Article>>(){}.getType();

    @Autowired
    public void setGson(Gson gson) {
        this.gson = gson;
    }

    public List<Article> fetchAllArticles(){
        try {
            JsonReader reader = new JsonReader(new FileReader(filePath));
            List<Article> articles = gson.fromJson(reader, ARTICLE_LIST_TYPE);
            reader.close();
            return articles;
        } catch (IOException e) {
            e.printStackTrace();
            return List.of();
        }
    }
}