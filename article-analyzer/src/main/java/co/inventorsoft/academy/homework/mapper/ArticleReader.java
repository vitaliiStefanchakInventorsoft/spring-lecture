package co.inventorsoft.academy.homework.mapper;

import co.inventorsoft.academy.homework.model.Article;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
public class ArticleReader {
    private final Gson gson;

    public List<Article> jsonToListArticles(File jsonFile) {
        Type articleListType = new TypeToken<List<Article>>() {
        }.getType();
        try (FileReader reader = new FileReader(jsonFile)) {
            return gson.fromJson(reader, articleListType);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return new ArrayList<>();
    }
}
