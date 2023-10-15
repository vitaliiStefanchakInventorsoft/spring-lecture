package com.articleanalyzer.core.repository;

import com.articleanalyzer.core.models.Article;
import com.articleanalyzer.core.models.User;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Repository;

import java.io.*;
import java.lang.reflect.Type;
import java.util.List;

@Repository
public class UserRepository {
    @Value("${user.file.name}")
    private String userFileName;
    private Gson gson;

    public UserRepository(Gson gson) {
        this.gson = gson;
    }

    public List<User> fetchUsers() {
        Type type = new TypeToken<List<User>>() {
        }.getType();
        try {
            return gson.fromJson(new FileReader("./src/main/resources/" + userFileName), type);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}
