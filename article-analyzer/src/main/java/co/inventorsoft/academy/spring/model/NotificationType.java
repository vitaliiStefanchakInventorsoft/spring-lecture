package co.inventorsoft.academy.spring.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

public enum NotificationType {
    EMAIL,
    SLACK
}
