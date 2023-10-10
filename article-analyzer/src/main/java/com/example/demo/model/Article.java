package com.example.demo.model;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class Article {
  private Long id;
  private String name;
  private String content;
}
