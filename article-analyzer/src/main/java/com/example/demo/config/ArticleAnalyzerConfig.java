package com.example.demo.config;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Configuration
public class ArticleAnalyzerConfig {

    @Value("${helper.words}")
    private String[] helperWords;

    @Bean
    public Set<String> getHelperWordsSet() {
        return new HashSet<>(Arrays.asList(helperWords));
    }

    @Bean
    public Gson gson() {
        return new Gson();
    }

}
