package com.inventorsoft.homework.config;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

  @Value("${excluded.words}")
  private String excludedWords;

  @Bean
  public Set<String> excludedWordsSet() {
    return new HashSet<>(Arrays.asList(excludedWords.split(",")));
  }
}
