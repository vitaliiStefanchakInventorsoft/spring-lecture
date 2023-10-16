package co.inventorsoft.academy.spring.articleanalyzer.repository;

import co.inventorsoft.academy.spring.articleanalyzer.model.User;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

@Repository
public class UserRepository {
    private Gson gson;

    @Value("${read.user.file.path}")
    private String filePath;

    @Autowired
    public void setGson(Gson gson) {
        this.gson = gson;
    }
    private static final Type USER_LIST_TYPE = new TypeToken<List<User>>(){}.getType();

    public List<User> fetchAllUsers() {
        try {
            JsonReader reader = new JsonReader(new FileReader(filePath));
            List<User> users = gson.fromJson(reader, USER_LIST_TYPE);
            reader.close();
            return users;
        } catch (IOException e) {
            e.printStackTrace();
            return List.of();
        }
    }
}
