package co.inventorsoft.academy.spring.repository;

import co.inventorsoft.academy.spring.model.Article;
import co.inventorsoft.academy.spring.model.User;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import lombok.AllArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Repository;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

@Repository
@AllArgsConstructor
public class UserRepository {
    private final Gson gson;
    private static final String FILE_NAME = "users.json";

    public List<User> getUsers() {
        ClassPathResource resource = new ClassPathResource("users.json");
        try(InputStreamReader reader = new InputStreamReader(resource.getInputStream())) {
            return Arrays.asList(gson.fromJson(reader, User[].class));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
