package co.inventorsoft.academy.spring.repository;

import co.inventorsoft.academy.spring.model.User;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.List;

@RequiredArgsConstructor
@Repository
public class UserRepository {

    private final Gson gson;

    public List<User> fetchAllUsers(){
        Type listType = new TypeToken<List<User>>() {
        }.getType();
        try {
            return gson.fromJson(new FileReader("article-analyzer/src/main/resources/users.json"), listType);
        } catch (FileNotFoundException e) {
            e.getStackTrace();
            return null;
        }

    }
}
