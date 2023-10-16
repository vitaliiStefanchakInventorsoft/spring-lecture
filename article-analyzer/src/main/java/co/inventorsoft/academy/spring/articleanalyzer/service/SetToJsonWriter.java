package co.inventorsoft.academy.spring.articleanalyzer.service;

import co.inventorsoft.academy.spring.articleanalyzer.notifier.EmailNotificationService;
import co.inventorsoft.academy.spring.articleanalyzer.notifier.NotificationService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Set;

@Service
public class SetToJsonWriter {
    private Gson gson;

    @Value("${write.categories.file.path}")
    private String filePath;

    @Autowired
    public void setGson(Gson gson) {
        this.gson = gson;
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
