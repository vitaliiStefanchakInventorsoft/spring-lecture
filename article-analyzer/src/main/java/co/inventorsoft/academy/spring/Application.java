package co.inventorsoft.academy.spring;

import co.inventorsoft.academy.spring.Service.ArticleAnalyzerService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		ConfigurableApplicationContext applicationContext = SpringApplication.run(Application.class, args);
		ArticleAnalyzerService articleAnalyzerService = applicationContext.getBean(ArticleAnalyzerService.class);
		articleAnalyzerService.analyze();
	}

}
