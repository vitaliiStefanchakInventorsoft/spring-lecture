package co.inventorsoft.academy.spring.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Component
public class User {

    private Long id;
    private String username;
    private String email;
    private String slackId;
    private NotificationType notificationType;

}
