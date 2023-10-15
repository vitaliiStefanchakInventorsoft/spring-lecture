package com.inventorsoft.homework;

import com.inventorsoft.homework.service.ArticleAnalyzerService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class HomeworkApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(HomeworkApplication.class, args);

		ArticleAnalyzerService articleAnalyzerService = context.getBean(ArticleAnalyzerService.class);

		articleAnalyzerService.processArticlesAndNotify();
	}

}
