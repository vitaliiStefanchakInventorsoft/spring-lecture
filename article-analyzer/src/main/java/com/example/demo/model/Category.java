package com.example.demo.model;

import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class Category {
  private List<String> categoryList;
}
