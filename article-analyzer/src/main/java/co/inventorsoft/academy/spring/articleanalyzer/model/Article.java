package co.inventorsoft.academy.spring.articleanalyzer.model;

import lombok.Getter;

public class Article {
    private int id;
    @Getter
    private String name;
    @Getter
    private String content;

    public Article(int id, String name, String content) {
        this.id = id;
        this.name = name;
        this.content = content;
    }

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
