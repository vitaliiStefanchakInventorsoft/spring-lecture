package co.inventorsoft.academy.homework.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Article {
    private int id;

    private String name;

    private String content;
}
