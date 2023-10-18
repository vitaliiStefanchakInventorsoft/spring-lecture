package com.reader.article_analyzer;

import com.reader.article_analyzer.Model.Article;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.*;

@Component
public class ArticleCategoryAnalyzer {
    private final JsonFileService fileService;
    private final String[] excludedWordsArray;
    public ArticleCategoryAnalyzer(@Value("${excluded.words}") String excludedWords, JsonFileService fileService) {
        this.excludedWordsArray = excludedWords.split(", ");
        this.fileService = fileService;
    }

    public Set<String> analyzeArticleContent(String filePath) throws IOException {
        HashSet<String> allCategories = new HashSet<>();
        List<Article> articles = fileService.readJsonFile(filePath, Article.class).stream().toList();
        String result;
        for (Article article : articles) {
            String content = article.getContent();
            result = content.replaceAll("[^\\sa-zA-Z0-9]", "");
            String[] wordsArray = result.split(" ");
            Map<String, Integer> wordCounts = new HashMap<>();


            for (String word : wordsArray) {
                word = word.toLowerCase();
                if (!isExcludedWord(word)) {
                    wordCounts.put(word, wordCounts.getOrDefault(word, 0) + 1);
                }
            }

            // Find the maximum frequency
            int maxCount = 0;
            for (int count : wordCounts.values()) {
                if (count > maxCount) {
                    maxCount = count;
                }
            }

            Set<String> categories = new HashSet<>();
            for (Map.Entry<String, Integer> entry : wordCounts.entrySet()) {
                if (entry.getValue() == maxCount || entry.getValue() == maxCount - 1) {
                    categories.add(entry.getKey());
                }
            }
           allCategories.add(categories.toString());
        }
        return allCategories;
    }

    private boolean isExcludedWord(String word) {
        for (String excludedWord : excludedWordsArray) {
            if (excludedWord.equalsIgnoreCase(word)) {
                return true;
            }
        }
        return false;
    }
}

