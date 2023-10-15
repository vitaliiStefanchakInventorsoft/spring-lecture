package co.inventorsoft.academy.spring.Service;

import co.inventorsoft.academy.spring.model.Article;
import co.inventorsoft.academy.spring.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ArticleService {
    private final ArticleRepository articleRepository;
    @Value("${excluded.words}")
    private final List<String> excludedWords;

    public List<List<String>> getArticleWords() {
        List<List<String>> collect = articleRepository.getArticles().stream()
                .map(Article::getContent)
                .map(content -> Arrays.stream(content.split("\\s+"))
                        .map(word -> word.replaceAll("\\p{Punct}", "").toLowerCase())
                        .filter(word -> !excludedWords.contains(word))
                        .collect(Collectors.toList()))
                .collect(Collectors.toList());
        return collect;
    }
}
