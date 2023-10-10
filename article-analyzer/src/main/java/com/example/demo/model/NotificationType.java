package com.example.demo.model;

public enum NotificationType {
  EMAIL("emailNotificationService"),
  SLACK("slackNotificationService");

  private final String serviceBeanName;

  NotificationType(String serviceBeanName) {
    this.serviceBeanName = serviceBeanName;
  }

  public String getServiceBeanName() {
    return serviceBeanName;
  }

}
