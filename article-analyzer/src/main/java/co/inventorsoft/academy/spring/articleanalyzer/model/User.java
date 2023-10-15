package co.inventorsoft.academy.spring.articleanalyzer.model;

import lombok.Getter;
import org.springframework.stereotype.Component;

@Component
public class User {
    private Long id;
    private String username;
    @Getter
    private String email;
    private String slackId;
    @Getter
    private NotificationType notificationType;

    public User(Long id, String username, String email, String slackId, NotificationType notificationType) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.slackId = slackId;
        this.notificationType = notificationType;
    }

    public User() {
    }

    public String toString() {
        return "User{id=" + this.id
                + ", username='" + this.username + '\''
                + ", email='" + this.email + '\''
                + ", slackId='" + this.slackId + '\''
                + ", notificationType=" + this.notificationType + '}';
    }
}
