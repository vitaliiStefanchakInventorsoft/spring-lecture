package com.articleanalyzer.core.services;

import com.articleanalyzer.core.models.Article;
import com.articleanalyzer.core.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class ArticleService {
    private final ArticleRepository articleRepository;
    @Value("${helper.words}")
    private String excludedWordsString;

    @Autowired
    public ArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public List<List<String>> getAllArticleWords() {
        List<List<String>> allWords = new ArrayList<>();
        List<String> excludedWords = Arrays.stream(excludedWordsString.split("\s*,\s*")).toList();
        for (Article article : articleRepository.fetchArticles()) {
            List<String> articleWords = new ArrayList<>(Arrays.stream(article.getContent().split("\\p{P}?[ \\t\\n\\r]+")).toList());
            articleWords.removeIf(excludedWords::contains);
            allWords.add(articleWords);
        }

        return allWords;
    }
}
