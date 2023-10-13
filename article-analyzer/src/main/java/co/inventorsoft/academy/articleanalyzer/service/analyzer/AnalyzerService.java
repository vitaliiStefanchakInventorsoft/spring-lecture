package co.inventorsoft.academy.articleanalyzer.service.analyzer;

import co.inventorsoft.academy.articleanalyzer.model.Article;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class AnalyzerService {
    List<String> analyzeResult = new ArrayList<>();
    @Value("${excluded.words}")
    private String excludedWords;

    private String[] getWordsArray(Article article, String excludedWords) {
        String[] wordsArray = article
                .getContent()
                .replaceAll(excludedWords, " ")
                .replaceAll("\\s+", " ")
                .split(" ");
        return wordsArray;
    }

    private List<Map<String, Integer>> getWordsAndRepeatCountList(Set<Article> articles, String excludedWords) {
        List<Map<String, Integer>> wordsAndRepeatCountList = new ArrayList<>();
        articles.forEach(article -> {

            Map<String, Integer> wordsAndRepeatCount = new HashMap<>();
            Set<String> excluded = new HashSet<>();
            String[] wordsArray = getWordsArray(article, excludedWords);

            for (int i = 0; i < wordsArray.length; i++) {
                int repeatCounter = 0;
                for (int j = i; j < wordsArray.length; j++) {
                    if (wordsArray[i].equalsIgnoreCase(wordsArray[j]) && !excluded.contains(wordsArray[i].toLowerCase())) {
                        ++repeatCounter;
                    }
                }

                excluded.add(wordsArray[i].toLowerCase());

                if (!wordsAndRepeatCount.containsKey(wordsArray[i].toLowerCase())) {
                    wordsAndRepeatCount.put(wordsArray[i].toLowerCase(), repeatCounter);
                }
            }
            wordsAndRepeatCountList.add(wordsAndRepeatCount);
        });
        return wordsAndRepeatCountList;
    }

    private int getMostRepeatedWordCount(Map<String, Integer> m) {
        return m.entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getValue)
                .orElse(0);
    }

    private void collectResults(int mostRepeatedWordCount, Map<String, Integer> m) {
        m.entrySet().forEach(kv -> {
            if (kv.getValue().equals(mostRepeatedWordCount)) {
                analyzeResult.add(kv.getKey().toUpperCase());
            }
        });
    }

    private String getExcludedWords() {
        String[] words = excludedWords.split(",");
        String escapedWords = Arrays.stream(words)
                .map(Pattern::quote)
                .collect(Collectors.joining("|"));
        return "\\b(" + escapedWords + ")\\b|\\W+";
    }

    public void analyze(Set<Article> articles) {
        excludedWords = getExcludedWords();
        if (articles == null) {
            throw new RuntimeException("Articles are empty");
        }
        List<Map<String, Integer>> wordsAndRepeatCountList = getWordsAndRepeatCountList(articles, excludedWords);

        wordsAndRepeatCountList.forEach(m -> {
            int mostRepeatedWordCount = getMostRepeatedWordCount(m);
            collectResults(mostRepeatedWordCount, m);
        });

    }

    public List<String> getCategories() {
        if (analyzeResult == null)
            throw new RuntimeException("Analyze all categories first");
        return analyzeResult;
    }
}
