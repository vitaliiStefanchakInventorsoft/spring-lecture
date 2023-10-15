package com.articleanalyzer.core;

import com.articleanalyzer.core.models.Article;
import com.articleanalyzer.core.repository.ArticleRepository;
import com.articleanalyzer.core.repository.CategoryRepository;
import com.articleanalyzer.core.services.ArticleAnalyzer;
import com.articleanalyzer.core.services.CategoryService;
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
