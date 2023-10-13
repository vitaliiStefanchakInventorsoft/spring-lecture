package co.inventorsoft.academy.spring.service;

import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CategoryService {
    public Set<String> getCategories(List<List<String>> splitWords) {
        Map<String, Integer> wordCountMap = new HashMap<>();

        for (List<String> sentence : splitWords) {
            for (String word : sentence) {
                wordCountMap.put(word, wordCountMap.getOrDefault(word, 0) + 1);
            }
        }

        int maxCount = Collections.max(wordCountMap.values());

        return wordCountMap.entrySet().stream()
                .filter(entry -> entry.getValue() == maxCount)
                .map(Map.Entry::getKey)
                .collect(Collectors.toSet());
    }
}
