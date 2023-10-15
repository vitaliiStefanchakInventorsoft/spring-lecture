package co.inventorsoft.academy.spring.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


/**
 * Model for Users who can write articles and receive notifications about their
 * articles been proceeded.
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class User {

    private Long id;
    private String username;
    private String email;
    private String slackId;
    private NotificationType notificationType;
}
