package co.inventorsoft.academy.homework.service.impl;

import co.inventorsoft.academy.homework.model.Article;
import co.inventorsoft.academy.homework.service.ContentProcessorService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class ContentProcessorServiceImpl implements ContentProcessorService {

    @Value("#{'${helper.words}'.split(', ')}")
    private Set<String> helperWords;

    public Set<String> process(List<Article> articles) {
        Map<String, Integer> wordCounts = new HashMap<>();

        for (Article article : articles) {
            String[] words = article.getContent().toLowerCase().split("\\W+");
            for (String word : words) {
                if (!helperWords.contains(word)) {
                    wordCounts.put(word, wordCounts.getOrDefault(word, 0) + 1);
                }
            }
        }

        int maxCount;
        if (!wordCounts.isEmpty()) {
            maxCount = Collections.max(wordCounts.values());
        } else {
            maxCount = 0;
            System.out.println("No words to process");
        }

        return wordCounts.entrySet().stream()
                .filter(entry -> entry.getValue() == maxCount)
                .map(Map.Entry::getKey)
                .collect(Collectors.toSet());
    }
}