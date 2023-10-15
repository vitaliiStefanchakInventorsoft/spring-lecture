package co.inventorsoft.academy.spring.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class User {
    private Long id;
    private String username;
    private String email;
    private String slackId;
    private NotificationType notificationType;
}
