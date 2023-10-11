package co.inventorsoft.academy.homework;

import co.inventorsoft.academy.homework.config.HelperWordsConfig;
import co.inventorsoft.academy.homework.model.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Component
public class ContentProcessor {
    private final HelperWordsConfig helperWordsConfig;

    @Autowired
    public ContentProcessor(HelperWordsConfig helperWordsConfig) {
        this.helperWordsConfig = helperWordsConfig;
    }

    public Set<String> process(List<Article> articles) {
        Map<String, Integer> wordCounts = new HashMap<>();

        for (Article article : articles) {
            String[] words = article.getContent().toLowerCase().split(" ");
            for (String word : words) {
                if (!helperWordsConfig.getHelperWords().contains(word)) {
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
