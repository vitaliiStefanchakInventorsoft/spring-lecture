package co.inventorsoft.academy.spring.repository;

import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Arrays;
import java.util.Set;

@Repository
@AllArgsConstructor
public class CategoryRepository {

    private final Gson gson;
    public void saveCategories(Set<String> categories) {
        try(Writer writer = new FileWriter("article-analyzer/src/main/resources/categories.json")){

            gson.toJson(categories, writer);

        } catch (IOException e) {
            System.out.println(Arrays.toString(e.getStackTrace()));
        }
    }

}
