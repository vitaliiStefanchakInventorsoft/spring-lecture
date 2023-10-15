package co.inventorsoft.academy.articleanalyzer.service.jsonmanager;

import com.google.gson.Gson;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Set;

@Service
public class JsonManagerService<T> {

    private final Gson gson;

    private final String projectRoot;

    public JsonManagerService(Gson gson) {
        this.gson = gson;
        this.projectRoot = System.getProperty("user.dir");
    }

    public Set<T> readAll(String path, Type type) {

        try (FileReader reader = new FileReader(projectRoot + path)) {
            return gson.fromJson(reader, type);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void save(List<String> categories, String path) {
        String json = gson.toJson(categories);
        try (FileWriter writer = new FileWriter(projectRoot + path)) {
            writer.write(json);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("\n\nFile: categories.json " + " has been created in: ..." + path + " <------------------\n");
    }

}
