package co.inventorsoft.academy.spring.articleanalyzer.model;

import lombok.Getter;

@Getter
public enum NotificationType {
    EMAIL("email"),
    SLACK("slack");

    private final String name;
    NotificationType(String name) {
        this.name = name;
    }
}
