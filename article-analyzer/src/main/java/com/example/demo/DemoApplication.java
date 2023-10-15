package com.example.demo;

import com.example.demo.service.ArticleService;
import com.sun.tools.javac.Main;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {

		ConfigurableApplicationContext applicationContext = SpringApplication.run(DemoApplication.class, args);

		ArticleService articleService = applicationContext.getBean(ArticleService.class);
		articleService.processArticles();
		articleService.notifyUsers();
	}


}
