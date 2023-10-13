package co.inventorsoft.academy.spring.repository;

import co.inventorsoft.academy.spring.model.User;
import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Repository
@AllArgsConstructor
public class UserRepository {
    private final Gson gson;

    public List<User> getUsers() {
        try {
            ClassPathResource resource = new ClassPathResource("users.json");

            try (InputStreamReader reader = new InputStreamReader(resource.getInputStream())) {
                return Arrays.asList(gson.fromJson(reader, User[].class));
            }
        } catch (IOException e) {
            System.out.println(Arrays.toString(e.getStackTrace()));
            return Collections.emptyList();
        }
    }
}
