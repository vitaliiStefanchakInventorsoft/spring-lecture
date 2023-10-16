package com.articleanalyzer.core;

import com.articleanalyzer.core.services.ArticleAnalyzer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class ArticleAnalyzerApplication {

    public static void main(String[] args) {
        ApplicationContext applicationContext = SpringApplication.run(ArticleAnalyzerApplication.class, args);
        ArticleAnalyzer analyzer = applicationContext.getBean(ArticleAnalyzer.class);
        analyzer.analyze();
    }

}
