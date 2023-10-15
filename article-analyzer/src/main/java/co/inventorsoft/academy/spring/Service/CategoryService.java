package co.inventorsoft.academy.spring.Service;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CategoryService {
    public Set<String> getCategories(List<List<String>> words) {
        Map<String, Integer> wordCounts = new HashMap<>();

        for (List<String> list : words) {
            for (String word : list) {
                wordCounts.put(word, wordCounts.getOrDefault(word, 0) + 1);
            }
        }

        int maxCount = 0;
        for (int count : wordCounts.values()) {
            maxCount = Math.max(maxCount, count);
        }

        Set<String> categories = new HashSet<>();
        for (Map.Entry<String, Integer> entry : wordCounts.entrySet()) {
            if (entry.getValue() == maxCount) {
                categories.add(entry.getKey());
            }
        }

        return categories;
    }
}
