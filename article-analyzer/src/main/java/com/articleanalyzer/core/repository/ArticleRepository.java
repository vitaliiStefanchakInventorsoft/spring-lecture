package com.articleanalyzer.core.repository;

import com.articleanalyzer.core.models.Article;
import com.articleanalyzer.core.models.User;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Repository;

import java.io.*;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

@Repository
public class ArticleRepository {
    private final Gson gson;

    @Value("${article.file.name}")
    private String articleFileName;

    @Autowired
    public ArticleRepository(Gson gson) {
        this.gson = gson;
    }

    public List<Article> fetchArticles() {
        Type type = new TypeToken<List<Article>>() {
        }.getType();
        try {
            return gson.fromJson(new FileReader("./src/main/resources/" + articleFileName), type);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}
