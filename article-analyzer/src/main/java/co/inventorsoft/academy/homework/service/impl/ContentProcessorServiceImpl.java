package co.inventorsoft.academy.homework.service.impl;

import co.inventorsoft.academy.homework.model.Article;
import co.inventorsoft.academy.homework.service.ContentProcessorService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Component
public class ContentProcessorServiceImpl implements ContentProcessorService {

    @Value("#{'${helper.words}'.split(', ')}")
    private Set<String> helperWords;

    public Set<String> process(List<Article> articles) {
        Map<String, Integer> wordCounts = new HashMap<>();

        for (Article article : articles) {
            String[] words = article.getContent().toLowerCase().split(" ");
            for (String word : words) {
                if (!helperWords.contains(word)) {
                    wordCounts.put(word, wordCounts.getOrDefault(word, 0) + 1);
                }
            }
        }

        int maxCount = Collections.max(wordCounts.values());

        Set<String> categories = new HashSet<>();

        for (Map.Entry<String, Integer> entry : wordCounts.entrySet()) {
            if (entry.getValue() == maxCount) {
                categories.add(entry.getKey());
            }
        }

        return categories;
    }
}