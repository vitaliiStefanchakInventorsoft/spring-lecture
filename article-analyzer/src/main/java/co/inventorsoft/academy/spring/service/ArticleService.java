package co.inventorsoft.academy.spring.service;

import co.inventorsoft.academy.spring.repository.ArticleRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.stream.Stream;

@Data
@Service
public class ArticleService {

    @Value("${helper.words}")
    private List<String> helperWords;

    private final ArticleRepository articleRepository;

    public List<List<String>> splitArticleWords(){

        return articleRepository.fetchAllArticles()
                .stream()
                .flatMap(article -> Stream.of(article.getContent()))
                .map(content->content.replaceAll("\\p{Punct}+", ""))
                .map(String::toLowerCase)
                .map(content-> Stream.of(content.split("\\s+"))
                        .filter(s -> !helperWords.contains(s))
                        .toList())
                .toList();
    }

}
