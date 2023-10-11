package co.inventorsoft.academy.spring.service;

import co.inventorsoft.academy.spring.model.Article;
import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Data
@RequiredArgsConstructor
@Service
public class CategoriesService {

    private final Gson gson;

    public void createCategoriesFile(List<List<String>> splitWords) throws IOException {
        try (Writer writer = new FileWriter("article-analyzer/src/main/resources/categories.json")) {

            List<String> categories = new ArrayList<>();

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

            gson.toJson(categories, writer);
        }
    }
}
