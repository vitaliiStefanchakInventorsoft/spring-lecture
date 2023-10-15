package co.inventorsoft.academy.articleanalyzer.repository;

import co.inventorsoft.academy.articleanalyzer.model.User;
import co.inventorsoft.academy.articleanalyzer.service.jsonmanager.JsonManagerService;
import com.google.gson.reflect.TypeToken;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Type;
import java.util.Set;

@Repository
@AllArgsConstructor
public class UserRepository {

    private final JsonManagerService<User> jsonManagerService;
    private final String usersPath;

    public Set<User> readAllUsers() {
        Type typeToken = new TypeToken<Set<User>>() {
        }.getType();
        return jsonManagerService.readAll(usersPath, typeToken);
    }

}
