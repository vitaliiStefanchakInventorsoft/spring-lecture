package com.example.demo.service;

import com.example.demo.files.JsonFileReader;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Set;

@Service
public class CategoryService {

    private JsonFileReader jsonFileReader;

    private String categoriesFilePath = "article-analyzer/src/main/resources/categories.json";

    private Gson gson;

    @Autowired
    public void setGson(Gson gson) {
        this.gson = gson;
    }

    public CategoryService(JsonFileReader jsonFileReader) {
        this.jsonFileReader = jsonFileReader;
    }

    public void saveUniqueCategories(Set<String> categories) {
        ClassPathResource resource = new ClassPathResource("article-analyzer/src/main/resources/categories.json");
        File file = null;
        try {
            file = resource.getFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String jsonCategories = gson.toJson(categories);
        if (file != null) {
            try (FileWriter fileWriter = new FileWriter(categoriesFilePath)) {
                fileWriter.write("");
                fileWriter.write(jsonCategories);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            File file1 = new File("article-analyzer/src/main/resources/categories.json");
            try (FileWriter fileWriter = new FileWriter(file1.getPath())) {
                fileWriter.write(jsonCategories);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
