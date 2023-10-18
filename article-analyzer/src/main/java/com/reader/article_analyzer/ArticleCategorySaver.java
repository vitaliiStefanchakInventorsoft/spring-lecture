package com.reader.article_analyzer;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Set;

@Component
public class ArticleCategorySaver {
    private final JsonFileService jsonFileService;

    public ArticleCategorySaver(JsonFileService jsonFileService) {
        this.jsonFileService = jsonFileService;
    }

    public void saveCategories(Set<String> categories) throws IOException {
        jsonFileService.writeJsonFile(categories);
    }
}
