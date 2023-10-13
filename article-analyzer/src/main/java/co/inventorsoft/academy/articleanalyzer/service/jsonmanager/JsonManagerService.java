package co.inventorsoft.academy.articleanalyzer.service.jsonmanager;

import co.inventorsoft.academy.articleanalyzer.model.Categories;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Set;

@Service
public class JsonManagerService<T> {

    @Autowired
    private Gson gson;

    private Set<T> data;

    public Set<T> readAll(String path, Type type) {
        try {
            FileReader reader = new FileReader(path);
            data = gson.fromJson(reader, type);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return data;
    }


    public void save(List<String> categories, String path, String fileName) {
        String json = gson.toJson(categories);
        save(json, path, fileName);
    }

    public void save(Categories categories, String path, String fileName) {
        String json = gson.toJson(categories);
        save(json, path, fileName);
    }

    private void save(String json, String path, String fileName) {
        try {
            FileWriter writer = new FileWriter(String.format("%s%s.json", path, fileName));
            writer.write(json);
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("\n\nFile: " + fileName + ".json " + " has been created in: " + path+ " <------------------\n");
    }


}
