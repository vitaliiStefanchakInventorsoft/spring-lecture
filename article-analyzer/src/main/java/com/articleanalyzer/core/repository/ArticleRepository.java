package com.articleanalyzer.core.repository;

import com.articleanalyzer.core.models.Article;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.List;

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
