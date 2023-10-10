package com.example.demo.model;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class User {
  private Long id;
  private String userName;
  private NotificationType notificationType;
  private String email;
  private String slackId;
}
