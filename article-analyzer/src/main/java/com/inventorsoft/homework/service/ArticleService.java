package com.inventorsoft.homework.service;

import com.inventorsoft.homework.entity.Article;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArticleService {

  private final Set<String> excludedWordsSet;

  @Autowired
  public ArticleService(Set<String> excludedWordsSet) {
    this.excludedWordsSet = excludedWordsSet;
  }

  public Set<String> processArticleContent(List<Article> articles) {
    Set<String> wordSet = new HashSet<>();

    for (Article article : articles) {
      String content = article.getContent().toLowerCase()
          .replaceAll("[^a-z\\s]", "")
          .replace("\n", " ");

      Map<String, Long> wordCounts = Arrays.stream(content.split(" "))
          .filter(s -> !s.isBlank() && !excludedWordsSet.contains(s))
          .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

      long maxCount = wordCounts.values().stream().max(Long::compare).orElse(0L);

      wordSet.addAll(wordCounts.entrySet().stream()
          .filter(entry -> entry.getValue() == maxCount)
          .map(Map.Entry::getKey)
          .collect(Collectors.toSet()));
    }

    return wordSet;
  }
}
