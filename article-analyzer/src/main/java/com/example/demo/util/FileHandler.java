package com.example.demo.util;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Set;
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

  public void saveSetToJsonFile(Set<String> data, String filePath) {
    try {
      Gson gson = new Gson();
      String jsonString = gson.toJson(data);
      Path directoryPath = Paths.get(filePath).getParent();
      if (!Files.exists(directoryPath)) {
        Files.createDirectories(directoryPath);
      }
      try (FileWriter fileWriter = new FileWriter(filePath)) {
        fileWriter.write(jsonString);
      }
      log.info("set saved to {}", filePath);
    } catch (IOException e) {
      throw new RuntimeException(e.getMessage());
    }
  }

}
