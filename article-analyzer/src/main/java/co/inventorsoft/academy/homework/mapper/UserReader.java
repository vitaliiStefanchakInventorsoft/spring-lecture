package co.inventorsoft.academy.homework.mapper;

import co.inventorsoft.academy.homework.model.User;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
public class UserReader {
    private final Gson gson;

    public List<User> jsonToListUsers(File jsonFile) {
        Type usersListType = new TypeToken<List<User>>() {
        }.getType();

        try (FileReader reader = new FileReader(jsonFile)) {
            return gson.fromJson(reader, usersListType);
        } catch (IOException e) {
            System.out.println("Input-output error: " + e.getMessage());
        }
        return new ArrayList<>();
    }

}
