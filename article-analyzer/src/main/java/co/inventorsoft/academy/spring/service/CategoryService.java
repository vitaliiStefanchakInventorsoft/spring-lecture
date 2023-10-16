package co.inventorsoft.academy.spring.service;

import co.inventorsoft.academy.spring.config.HelperWordsProperties;
import co.inventorsoft.academy.spring.model.Article;
import co.inventorsoft.academy.spring.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

@Service
public class CategoryService {
    @Autowired
    private HelperWordsProperties helperWordsProperties;
    @Autowired
    private ArticleRepository articleRepository;

    public Set<String> extractCategories() {
        Set<String> result = new HashSet<>();
        List<Article> articles = articleRepository.getArticlesFromJson();

        for (Article article : articles) {
            Optional<String> content = Optional.ofNullable(article.getContent());

            if (content.isPresent()) {
                String[] words = content.get().toLowerCase().split("\\W+");
                Set<String> categories = getCategories(getWordsCount(words));
                result.addAll(categories);
            }
        }

        return result;
    }

    private Map<String, Integer> getWordsCount(String[] words) {
        Map<String, Integer> wordsCount = new HashMap<>();
        Set<String> excluded = helperWordsProperties.getWords();

        for (String word : words) {
            if (!excluded.contains(word)) {
                wordsCount.put(word, wordsCount.getOrDefault(word, 0) + 1);
            }
        }

        return wordsCount;
    }

    private Set<String> getCategories(Map<String, Integer> words) {
        Set<String> result = new HashSet<>();

        int max = Collections.max(words.values());

        for (Map.Entry<String, Integer> set : words.entrySet()) {
            if (set.getValue() == max) {
                result.add(set.getKey());
            }
        }

        return result;
    }

}
