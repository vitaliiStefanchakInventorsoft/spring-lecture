package co.inventorsoft.academy.spring.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Article {
    private Long id;
    private String name;
    private String content;
}
