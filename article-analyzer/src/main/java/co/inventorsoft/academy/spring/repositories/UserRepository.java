package co.inventorsoft.academy.spring.repositories;

import co.inventorsoft.academy.spring.models.User;
import co.inventorsoft.academy.spring.utils.UUIDDeserializer;
import co.inventorsoft.academy.spring.utils.UUIDSerializer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

/**
 * Data access layer for User entity.
 */
@Repository
//public interface UserRepository extends JpaRepository<User, UUID> {
public class UserRepository {
    @Value("${USERS_FILE}")
    String usersFile;
    private final Gson gson;

    @Autowired
    public UserRepository() {
        this.gson = new GsonBuilder()
            .registerTypeAdapter(UUID.class, new UUIDDeserializer())
            .registerTypeAdapter(UUID.class, new UUIDSerializer())
            .create();
    }

    public Set<User> getAllUsers() {
        try (Reader reader = new InputStreamReader(
            Objects.requireNonNull(getClass().getResourceAsStream(this.usersFile)))) {
            Type userListType = new TypeToken<Set<User>>(){}.getType();
            return gson.fromJson(reader, userListType);
        } catch (IOException e) {
            System.out.println("Something went wrong\n" + e.getMessage());
        }
        return new HashSet<>();
    }
    public Optional<User> getUserById(UUID userId){
        for(User user : getAllUsers()){
            if(user.getId().equals(userId)){
                return Optional.of(user);
            }
        }
        throw new IllegalArgumentException("User not found");
    }

}
