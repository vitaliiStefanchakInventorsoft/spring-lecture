package com.inventorsoft.homework.utility;

import com.google.gson.Gson;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import org.springframework.stereotype.Component;

@Component
public class JsonUtil {

  public <T> List<T> readFromJson(String fileName, Class<T[]> clazz) {
    Gson gson = new Gson();
    try (InputStream is = getClass().getClassLoader().getResourceAsStream(fileName);
        Reader reader = new InputStreamReader(is, StandardCharsets.UTF_8)) {
      T[] array = gson.fromJson(reader, clazz);
      return Arrays.asList(array);
    } catch (IOException e) {
      e.printStackTrace();
      return Collections.emptyList();
    }
  }

  public void saveToJson(String fileName, Set<String> set) {
    Gson gson = new Gson();
    String fullPath = "article-analyzer/src/main/resources/" + fileName;
    try {
      FileWriter myWriter = new FileWriter(fullPath);
      myWriter.write(gson.toJson(set));
      myWriter.close();
    } catch (IOException e) {
      e.printStackTrace();
    }

  }
}
