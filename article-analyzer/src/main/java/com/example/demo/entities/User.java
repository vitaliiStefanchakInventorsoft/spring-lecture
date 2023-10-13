package com.example.demo.entities;

import com.example.demo.enums.NotificationType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
public class User {

    private Integer id;

    private String username;

    private String email;

    private String slackId;

    private NotificationType notificationType;

}
