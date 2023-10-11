package co.inventorsoft.academy.homework;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.lang.reflect.Type;
import java.util.HashSet;
import java.util.Set;

@Component
public class CategorySaver {
    private static final String FILEPATH = "src/main/resources/categories.json";

    public void saveCategory(Set<String> newCategories) {
        Gson gson = new Gson();

        Set<String> allCategories = new HashSet<>(newCategories);

        File file = new File(FILEPATH);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.out.println("Creating error " + e.getMessage());
            }
        }

        Type setType = new TypeToken<Set<String>>() {
        }.getType();

        try {
            allCategories.addAll(gson.fromJson(new FileReader(file), setType));
        } catch (FileNotFoundException e) {
            System.out.println("File not found " + e.getMessage());
        }

        try (Writer writer = new FileWriter(file)) {
            gson.toJson(allCategories, writer);
        } catch (IOException e) {
            System.out.println("Writing error " + e.getMessage());
        }
    }
}
