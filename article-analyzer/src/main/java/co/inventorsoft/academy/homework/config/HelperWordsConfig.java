package co.inventorsoft.academy.homework.config;


import lombok.Getter;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Value;


import java.util.Set;

@Getter
@Component
public class HelperWordsConfig {

    @Value("#{'${helper.words}'.split(', ')}")
    private Set<String> helperWords;

}
