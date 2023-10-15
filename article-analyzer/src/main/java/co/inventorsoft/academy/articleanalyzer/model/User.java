package co.inventorsoft.academy.articleanalyzer.model;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class User {
    private Integer id;

    @SerializedName("username")
    private String userName;
    private String email;
    private String slackId;
    private NotificationType notificationType;
}
