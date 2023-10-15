package co.inventorsoft.academy.spring.config;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class ApplicationConfig {
    @Value("${excluded.words}")
    private List<String> excludedWords;

    @Bean
    public List<String> getExcludedWords() {
        return excludedWords;
    }

    @Bean
    public Gson gson() {
        return new Gson();
    }
}
