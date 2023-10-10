package com.example.demo.model;

import lombok.Getter;

@Getter
public class Message {
  private final String text;
  private final User  receiver;

  public Message(String text, User receiver) {
    this.text = text;
    this.receiver = receiver;
  }
}
