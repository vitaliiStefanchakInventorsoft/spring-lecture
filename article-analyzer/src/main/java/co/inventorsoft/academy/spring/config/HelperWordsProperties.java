package co.inventorsoft.academy.spring.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Set;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "helper")
public class HelperWordsProperties {
    private Set<String> words;
}
