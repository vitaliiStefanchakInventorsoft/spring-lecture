package co.inventorsoft.academy.spring.service;

import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class CategoryService {

    public Set<String> fetchCategories(List<List<String>> splitWords){
            Set<String> categories = new HashSet<>();

            splitWords.forEach(strings -> {

                Map<String, Integer> countMap = new HashMap<>();

                strings.forEach(s -> {
                    if (countMap.containsKey(s)) {
                        countMap.put(s, countMap.get(s) + 1);
                    } else {
                        countMap.put(s, 1);
                    }
                });

                int maxCountWord = (countMap
                        .entrySet()
                        .stream()
                        .max(Comparator.comparingInt(Map.Entry::getValue))
                        .map(Map.Entry::getValue)
                        .orElseThrow());

                categories.addAll(countMap
                        .entrySet()
                        .stream()
                        .filter(entry -> entry.getValue() == maxCountWord)
                        .map(Map.Entry::getKey)
                        .toList());

            });
            return categories;
    }
}
