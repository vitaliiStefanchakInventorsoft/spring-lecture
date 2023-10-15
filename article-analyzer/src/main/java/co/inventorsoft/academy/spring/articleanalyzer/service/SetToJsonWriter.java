package co.inventorsoft.academy.spring.articleanalyzer.service;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Set;

@Service("setToJsonWriter")
public class SetToJsonWriter {
    private final Gson gson;
    private final String filePath;

    SetToJsonWriter(Gson gson, @Value("${write.categories.file.path}") String filePath) {
        this.gson = gson;
        this.filePath = filePath;
    }

    public void write(Set<String> stringSet) {
        write(stringSet, filePath);
    }

    public void write(Set<String> stringSet, String filePath) {
        try (FileWriter writer = new FileWriter(filePath)) {
            gson.toJson(stringSet, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
