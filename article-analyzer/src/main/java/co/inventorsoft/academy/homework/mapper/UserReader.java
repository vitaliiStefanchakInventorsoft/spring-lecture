package co.inventorsoft.academy.homework.mapper;

import co.inventorsoft.academy.homework.model.User;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
public class UserReader {
    private final Gson gson;

    public List<User> jsonToListUsers(String jsonFile) {
        try (Reader reader = new InputStreamReader(getClass().getResourceAsStream("/" + jsonFile))) {
            Type userListType = new TypeToken<List<User>>(){}.getType();
            return gson.fromJson(reader, userListType);
        } catch (IOException e) {
            System.out.println("Error reading json " + e.getMessage());
        }
        return new ArrayList<>();
    }

}
