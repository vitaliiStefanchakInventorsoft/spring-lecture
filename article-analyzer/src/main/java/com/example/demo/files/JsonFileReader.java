package com.example.demo.files;

import com.example.demo.entities.Article;
import com.example.demo.entities.User;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class JsonFileReader {

    private Gson gson;

    @Autowired
    public void setGson(Gson gson) {
        this.gson = gson;
    }

    public List<Article> readArticlesFromJsonFile(String filePath) {
        try {
            String json = new String(Files.readAllBytes(Paths.get(filePath)));
            return gson.fromJson(json, new TypeToken<List<Article>>() {}.getType());
        } catch (IOException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    public List<User> readUsersFromJsonFile(String filePath) {
        try {
            String json = new String(Files.readAllBytes(Paths.get(filePath)));
            return gson.fromJson(json, new TypeToken<List<User>>() {}.getType());
        } catch (IOException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    public Set<String> readCategoriesFromFile(String filePath) {
        Set<String> existingCategories = new HashSet<>();

        try {
            String fileContents = new String(Files.readAllBytes(Paths.get(filePath)));
            existingCategories = gson.fromJson(fileContents, new TypeToken<Set<String>>() {}.getType());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return existingCategories;
    }

}
