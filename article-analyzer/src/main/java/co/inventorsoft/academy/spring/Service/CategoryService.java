package co.inventorsoft.academy.spring.Service;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class CategoryService {
    public Set<String> getCategories(List<List<String>> words) {

        Set<String> categories = new HashSet<>();
        categories.add(mostFrequentWord(words.get(0)));
        categories.add(mostFrequentWord(words.get(1)));
        categories.add(mostFrequentWord(words.get(2)));
        for (List<String> category: words) {
            categories.add(mostFrequentWord(category));
        }
        return categories;
    }

    private String mostFrequentWord(List<String> words) {
        Map<String, Integer> wordCounts = new HashMap<>();
        String category = "";
        for (String word : words) {
            wordCounts.put(word, wordCounts.getOrDefault(word, 0) + 1);
        }

        int maxCount = 0;
        for (int count : wordCounts.values()) {
            maxCount = Math.max(maxCount, count);
        }

        for (Map.Entry<String, Integer> entry : wordCounts.entrySet()) {
            if (entry.getValue() == maxCount) {
                category = entry.getKey();
            }
        }

        return category;
    }
}
