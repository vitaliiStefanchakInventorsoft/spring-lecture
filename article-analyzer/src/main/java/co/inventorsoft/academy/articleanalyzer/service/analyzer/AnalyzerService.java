package co.inventorsoft.academy.articleanalyzer.service.analyzer;

import co.inventorsoft.academy.articleanalyzer.model.Article;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class AnalyzerService {

    @Value("${excluded.words}")
    private String excludedWords;

    private String[] getWordsArray(Article article, String excludedWords) {
        return article
                .getContent()
                .replaceAll(excludedWords, " ")
                .replaceAll("\\s+", " ")
                .split(" ");
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

    private List<String> collectResults(int mostRepeatedWordCount, Map<String, Integer> m) {
        List<String> analyzeResult = new ArrayList<>();
        m.forEach((key, value) -> {
            if (value.equals(mostRepeatedWordCount)) {
                analyzeResult.add(key.toUpperCase());
            }
        });
        return analyzeResult;
    }

    private String getExcludedWords() {
        return "\\b(" + String.join("|", excludedWords.split(",")) + ")\\b|\\W+";
    }

    public List<String> analyze(Set<Article> articles) {
        List<String> analyzeResult = new ArrayList<>();
        String excludedWords = getExcludedWords();
        if (articles == null) {
            throw new RuntimeException("Articles are empty");
        }
        List<Map<String, Integer>> wordsAndRepeatCountList = getWordsAndRepeatCountList(articles, excludedWords);

        wordsAndRepeatCountList.forEach(m -> {
            int mostRepeatedWordCount = getMostRepeatedWordCount(m);
            analyzeResult.addAll(collectResults(mostRepeatedWordCount, m));
        });
        return analyzeResult;
    }
}
