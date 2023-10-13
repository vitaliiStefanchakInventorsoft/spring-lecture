package com.example.demo.service;

import com.example.demo.files.JsonFileReader;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;

@Service
public class CategoryService {

    @Autowired
    private JsonFileReader jsonFileReader;

//    @Value("${categories.file.path}")
    private String categoriesFilePath = "C:\\Users\\User\\IdeaProjects\\InventorSoft_Homework_3\\article-analyzer\\src\\main\\resources\\categories.json";

    private Gson gson;

    @Autowired
    public void setGson(Gson gson) {
        this.gson = gson;
    }

    public void saveUniqueCategories(Set<String> categories) {
        Set<String> existingCategories = jsonFileReader.readCategoriesFromFile(categoriesFilePath);

        existingCategories.addAll(categories);

        String jsonCategories = gson.toJson(existingCategories);

        try (FileWriter fileWriter = new FileWriter(categoriesFilePath)) {
            fileWriter.write(jsonCategories);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
