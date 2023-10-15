package co.inventorsoft.academy.spring.repository;

import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Set;

@Repository
@AllArgsConstructor
public class CategoryRepository {
    private Gson gson;
    private static final String FILE_PATH = "article-analyzer/src/main/resources/categories.json";

    public void saveCategories(Set<String> categories) {
        try(Writer writer = new FileWriter(FILE_PATH)) {
            gson.toJson(categories, writer);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
