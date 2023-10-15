package co.inventorsoft.academy.spring.articleanalyzer;

import co.inventorsoft.academy.spring.articleanalyzer.notifier.NotificationService;
import co.inventorsoft.academy.spring.articleanalyzer.repository.ArticleRepository;
import co.inventorsoft.academy.spring.articleanalyzer.repository.UserRepository;
import co.inventorsoft.academy.spring.articleanalyzer.service.SetToJsonWriter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import co.inventorsoft.academy.spring.articleanalyzer.model.Article;
import co.inventorsoft.academy.spring.articleanalyzer.service.CategoryService;

import java.util.List;

@SpringBootApplication
public class Main {
	public static void main(String[] args) {
		ConfigurableApplicationContext applicationContext = SpringApplication.run(Main.class, args);
		ArticleAnalyzer articleAnalyzer = applicationContext.getBean("articleAnalyzer", ArticleAnalyzer.class);
		articleAnalyzer.analyze();
		applicationContext.close();
	}
}
