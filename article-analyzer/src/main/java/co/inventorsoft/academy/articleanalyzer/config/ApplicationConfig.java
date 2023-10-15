package co.inventorsoft.academy.articleanalyzer.config;


import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {

    @Value("${articles.path}")
    private String articlesPath;

    @Value("${categories.path}")
    private String categoriesPath;

    @Value("${users.path}")
    private String usersPath;

    @Value("${excluded.words}")
    private String excludedWords;

    @Bean
    public Gson gson() {
        return new Gson();
    }

    @Bean
    public String projectRoot() {
        return System.getProperty("user.dir");
    }

    @Bean
    public String articlesPath() {
        return articlesPath;
    }

    @Bean
    public String categoriesPath() {
        return categoriesPath;
    }

    @Bean
    public String usersPath() {
        return usersPath;
    }

    @Bean
    public String excludedWords() {
        return excludedWords;
    }
}
