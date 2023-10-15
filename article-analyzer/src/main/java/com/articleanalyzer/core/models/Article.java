package com.articleanalyzer.core.models;

public class Article {
    private int id;
    private String name;
    private String content;

    public Article(int id, String name, String content) {
        this.id = id;
        this.name = name;
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String toString() {
        return "id:" + id + "\n" +
                "name:" + name + "\n" +
                "content:" + content;
    }
}
