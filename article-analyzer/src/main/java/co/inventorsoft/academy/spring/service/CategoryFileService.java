package co.inventorsoft.academy.spring.service;

import com.google.gson.Gson;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Set;

@Service
public class CategoryFileService {
    public void saveCategories(Set<String> categories) {
        File file = new File("article-analyzer/src/main/resources/categories.json");
        Gson gson = new Gson();
        String jsonBody = gson.toJson(categories);

        try ( FileWriter writer = new FileWriter(file);) {
            writer.write(jsonBody);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
