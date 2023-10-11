package co.inventorsoft.academy.homework.mapper;

import co.inventorsoft.academy.homework.model.Article;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;
@Component
public class ArticleMapper {
    public List<Article> map(File jsonFile) {
        Gson gson = new Gson();
        Type articleListType = new TypeToken<List<Article>>() {}.getType();
        List<Article> articles = null;
        try (FileReader reader = new FileReader(jsonFile)) {
            articles = gson.fromJson(reader, articleListType);
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        } catch (JsonSyntaxException e) {
            System.out.println("Error converting JSON: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Input-output error: " + e.getMessage());
        }
        return articles;

    }
}
