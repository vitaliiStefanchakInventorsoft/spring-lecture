package com.reader.article_analyzer;

import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class ArticleCategorySaver {
    private final ArticleCategoryAnalyzer articleCategoryAnalyzer;
    private final JsonFileService jsonFileService;

    public ArticleCategorySaver(ArticleCategoryAnalyzer articleCategoryAnalyzer, JsonFileService jsonFileService) {
        this.articleCategoryAnalyzer = articleCategoryAnalyzer;
        this.jsonFileService = jsonFileService;
    }

    public void articlesSaver() throws IOException {
        jsonFileService.writeJsonFile(articleCategoryAnalyzer.analyzeArticleContent("articles.json").stream().toList());
    }
}
