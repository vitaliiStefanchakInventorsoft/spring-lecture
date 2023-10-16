package com.articleanalyzer.core.services;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Set;
import java.util.Map;
import java.util.List;
import java.util.HashSet;

@Service
public class CategoryService {
    private ArticleService articleService;

    public CategoryService(ArticleService articleService) {
        this.articleService = articleService;
    }

    public Set<String> getCategories() {
        Map<String, Integer> wordCount = new HashMap<>();
        int maxCount = 0;
        Set<String> categories = new HashSet<>();

        for (List<String> articleWords : articleService.getAllArticleWords()) {

            for (String word : articleWords) {
                if (wordCount.containsKey(word.toLowerCase())) {
                    wordCount.put(word.toLowerCase(), wordCount.get(word.toLowerCase()) + 1);
                } else {
                    wordCount.put(word.toLowerCase(), 1);
                }
                maxCount = Math.max(maxCount, wordCount.get(word.toLowerCase()));
            }

            for (String category : wordCount.keySet()) {
                if (wordCount.get(category) == maxCount) {
                    categories.add(category);
                }
            }

            wordCount.clear();
            maxCount = 0;

        }
        System.out.println(categories);
        return categories;
    }
}
