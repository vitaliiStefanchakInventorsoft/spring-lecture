package co.inventorsoft.academy.spring.config;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.Set;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

/**
 * Configuration file.
 */

@org.springframework.context.annotation.Configuration
@ComponentScan("co.inventorsoft.academy.spring.models")
@ComponentScan("co.inventorsoft.academy.spring.repositories")
@ComponentScan("co.inventorsoft.academy.spring.services")
public class Configuration {
    @Value("${excluded.words}")
    private Set<String> excludedWords;

    @Bean
    public Set<String> excludedWords() {
        return excludedWords;
    }

    @Bean
    public Gson gson() {
        return new GsonBuilder().create();
    }

}
