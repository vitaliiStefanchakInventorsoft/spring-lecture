package com.example.demo.util;

import com.example.demo.model.Category;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


@Component
@Slf4j
public class FileHandler {

  public JsonArray getDataFromJsonFile(String filePath) {
    try (FileReader fileReader = new FileReader(filePath)) {
      log.info("file read");
      return JsonParser.parseReader(fileReader).getAsJsonArray();
    } catch (IOException e) {
      throw new RuntimeException(e.getMessage());
    }
  }

  public void saveDataToFile(Category categories, String categoriesFilePath) {
    try {
      Gson gson = new Gson();
      String jsonString = gson.toJson(categories);
      Path directoryPath = Paths.get(categoriesFilePath).getParent();
      if (!Files.exists(directoryPath)) {
        Files.createDirectories(directoryPath);
      }
      try (FileWriter fileWriter = new FileWriter(categoriesFilePath)) {
        fileWriter.write(jsonString);
      }
      log.info("Categories saved to {}", categoriesFilePath);
    } catch (IOException e) {
      throw new RuntimeException(e.getMessage());
    }
  }

}
