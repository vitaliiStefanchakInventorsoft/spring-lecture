package co.inventorsoft.academy.homework.service.impl;

import co.inventorsoft.academy.homework.service.CategorySaverService;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Component
@AllArgsConstructor
public class CategorySaverServiceImpl implements CategorySaverService {
    private static final String CATEGORIES_FILE = "categories.json";

    private final Gson gson;

    public void saveCategory(Set<String> newCategories) {
        Set<String> allCategories = new HashSet<>(newCategories);

        File file = new File(CATEGORIES_FILE);
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
            Collection<String> categoriesFromFile = gson.fromJson(new FileReader(file), setType);
            if (categoriesFromFile != null) {
                allCategories.addAll(categoriesFromFile);
            }
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