package co.inventorsoft.academy.homework.mapper;

import co.inventorsoft.academy.homework.model.User;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

@Component
public class UserMapper {
    private final Gson gson;

    @Autowired
    public UserMapper(Gson gson) {
        this.gson = gson;
    }

    public List<User> map(File jsonFile) {
        Gson gson = new Gson();

        Type usersListType = new TypeToken<List<User>>() {        }.getType();
        List<User> users = null;
        try (FileReader reader = new FileReader(jsonFile)) {
            users = gson.fromJson(reader, usersListType);
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        } catch (JsonSyntaxException e) {
            System.out.println("Error converting JSON: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Input-output error: " + e.getMessage());
        }
        return users;
    }

}
