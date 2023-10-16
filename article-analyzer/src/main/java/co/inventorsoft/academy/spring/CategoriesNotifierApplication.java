package co.inventorsoft.academy.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class CategoriesNotifierApplication {

	public static void main(String[] args) {

		ConfigurableApplicationContext applicationContext = SpringApplication.run(CategoriesNotifierApplication.class, args);
		ArticleService articleService = applicationContext.getBean(ArticleService.class);
		articleService.processCategories();
		articleService.pushNotifications();
	}

}
