package co.inventorsoft.academy.spring.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class User {
    private long id;
    private String username;
    private String email;
    private String slackId;
    private NotificationType notificationType;
}
