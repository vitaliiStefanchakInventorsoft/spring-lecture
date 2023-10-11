package co.inventorsoft.academy.homework.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class User {
    private Long id;

    private String username;

    private String email;

    private String slackId;

    private NotificationType notificationType;

}
