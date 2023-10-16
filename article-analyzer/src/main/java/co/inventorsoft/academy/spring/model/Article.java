package co.inventorsoft.academy.spring.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class Article {

    private int id;
    private String name;
    private String content;
}
