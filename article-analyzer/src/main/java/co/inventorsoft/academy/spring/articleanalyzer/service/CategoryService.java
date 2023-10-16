package co.inventorsoft.academy.spring.articleanalyzer.service;

import co.inventorsoft.academy.spring.articleanalyzer.model.Article;
import co.inventorsoft.academy.spring.articleanalyzer.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CategoryService {
    @Value("${excluded.words}")
    private List<String> excludedWords;

    public Set<String> findMostCommonCategories(List<Article> articles) {
        return articles.stream()
                .map(this::findMostCommonCategories)
                .flatMap(Set::stream)
                .collect(Collectors.toSet());
    }

    public Set<String> findMostCommonCategories(Article article) {
        Map<String, Long> wordFrequency = getWordCountMap(getWords(article));

        Long maxFrequency = wordFrequency.values().stream().max(Long::compareTo).orElse(0L);

        return wordFrequency.entrySet().stream()
                .filter(entry -> entry.getValue().equals(maxFrequency))
                .map(Map.Entry::getKey)
                .collect(Collectors.toSet());
    }

    private static String[] getWords(Article article) {
        return article.getContent().split("\\s+");
    }

    private Map<String, Long> getWordCountMap(String[] words) {
        return Arrays.stream(words)
                .map(word -> word.replaceAll("[^a-zA-Z]", "").toLowerCase())
                .filter(word -> !excludedWords.contains(word))
                .collect(Collectors.groupingBy(s -> s, Collectors.counting()));
    }
}