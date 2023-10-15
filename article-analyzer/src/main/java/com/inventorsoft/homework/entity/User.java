package com.inventorsoft.homework.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
  private Long id;
  private String username;
  private String email;
  private String slackId;
  private NotificationType notificationType;
}
