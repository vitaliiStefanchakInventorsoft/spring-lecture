package co.inventorsoft.academy.homework.config;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.nio.file.Path;
import java.nio.file.Paths;

@Configuration
public class SpringConfig {
    @Value("${CATEGORIES_FILE_PATH}")
    private String categoriesFilePath;
    @Bean
    public Path categoriesFilePath() {
        return Paths.get(categoriesFilePath);
    }
    @Bean
    public Gson gson(){
        return new Gson();
    }
}
