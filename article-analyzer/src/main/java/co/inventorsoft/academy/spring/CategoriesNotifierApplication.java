package co.inventorsoft.academy.spring;

import co.inventorsoft.academy.spring.model.Article;
import co.inventorsoft.academy.spring.repository.ArticleRepository;
import co.inventorsoft.academy.spring.repository.UserRepository;
import co.inventorsoft.academy.spring.service.CategoryFileService;
import co.inventorsoft.academy.spring.service.CategoryService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.List;
import java.util.Set;

@SpringBootApplication
public class CategoriesNotifierApplication {

	public static void main(String[] args) {

		ConfigurableApplicationContext applicationContext = SpringApplication.run(CategoriesNotifierApplication.class, args);
		ArticleService articleService = applicationContext.getBean(ArticleService.class);
		articleService.processCategories();
		articleService.pushNotifications();
	}

}
