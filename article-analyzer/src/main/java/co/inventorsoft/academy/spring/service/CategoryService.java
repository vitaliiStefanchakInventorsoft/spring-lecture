package co.inventorsoft.academy.spring.service;

import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class CategoryService {
    public Set<String> getCategories(List<List<String>> splitWords) {
        Set<String> result = new HashSet<>();

        for (List<String> sentence : splitWords) {
            Map<String, Integer> wordCountMap = new HashMap<>();

            for (String word : sentence) {
                wordCountMap.put(word, wordCountMap.getOrDefault(word, 0) + 1);
            }

            int maxCount = Collections.max(wordCountMap.values());

            for (Map.Entry<String, Integer> entry : wordCountMap.entrySet()) {
                if (entry.getValue() == maxCount) {
                    result.add(entry.getKey());
                }
            }
        }

        return result;
    }
}

