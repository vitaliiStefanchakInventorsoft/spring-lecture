package com.articleanalyzer.core.repository;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Set;

@Repository
public class CategoryRepository {
    private final Gson gson;
    @Value("${categories.file.name}")
    private String categoriesFileName;

    public CategoryRepository(Gson gson) {
        this.gson = gson;
    }

    public void saveCategories(Set<String> categories) {
        try (Writer writer = new FileWriter("./src/main/resources/" + categoriesFileName)) {
            gson.toJson(categories, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
