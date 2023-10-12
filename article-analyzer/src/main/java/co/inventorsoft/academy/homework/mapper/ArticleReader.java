package co.inventorsoft.academy.homework.mapper;

import co.inventorsoft.academy.homework.model.Article;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
public class ArticleReader {
    private final Gson gson;

    public List<Article> jsonToListArticles(String fileName) {
        InputStream is = getClass().getResourceAsStream("/" + fileName);
        if (is == null) {
            System.out.println("File not found: " + fileName);
            return new ArrayList<>();
        }

        try (Reader reader = new InputStreamReader(is)) {
            Type articleListType = new TypeToken<List<Article>>(){}.getType();
            return gson.fromJson(reader, articleListType);
        } catch (IOException e) {
            System.out.println("Error reading json " + e.getMessage());
        }
        return new ArrayList<>();
    }


}
