package co.inventorsoft.academy.spring;

import co.inventorsoft.academy.spring.service.ArticleAnalyzerService;
import co.inventorsoft.academy.spring.service.ArticleService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		ConfigurableApplicationContext applicationContext = SpringApplication.run(Application.class, args);

		ArticleAnalyzerService articleAnalyzerService = applicationContext.getBean(ArticleAnalyzerService.class);

		articleAnalyzerService.analyze();
	}

}
