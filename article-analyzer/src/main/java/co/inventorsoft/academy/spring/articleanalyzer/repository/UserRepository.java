package co.inventorsoft.academy.spring.articleanalyzer.repository;

import co.inventorsoft.academy.spring.articleanalyzer.model.User;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

@Repository("userRepository")
public class UserRepository {
    private final Gson gson;
    private final String filePath;
    private static final Type USER_LIST_TYPE = new TypeToken<List<User>>(){}.getType();


    UserRepository(Gson gson, @Value("${read.user.file.path}") String filePath) {
        this.gson = gson;
        this.filePath = filePath;
    }

    // Use the default file path
    public List<User> fetchAllUsers() {
        return fetchAllUsers(this.filePath);
    }

    // Read all user from the JSON file
    public List<User> fetchAllUsers(String filePath) {
        try {
            // Deserialize the JSON data into an array of Article objects
            JsonReader reader = new JsonReader(new FileReader(filePath));
            List<User> users = gson.fromJson(reader, USER_LIST_TYPE);
            reader.close();
            return users;
        } catch (IOException e) {
            e.printStackTrace();
            // return empty list if exception occurs
            return List.of();
        }
    }
}
