package com.example.demo.util;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import org.springframework.stereotype.Component;

@Component
public class JsonParserUtil {
  private final Gson gson;

  public JsonParserUtil(Gson gson) {
    this.gson = gson;
  }

  public <T> T[] parseJsonArray(JsonArray jsonArray, Class<T[]> clazz) {
    return gson.fromJson(jsonArray, clazz);
  }
}
