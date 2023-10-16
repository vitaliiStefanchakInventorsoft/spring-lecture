package co.inventorsoft.academy.spring.repository;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;

@Repository
public class CategoryRepository {
    @Autowired
    private Gson gson;
    public Set<String> getCategoriesFromJson() {
        InputStream is = getClass().getResourceAsStream("/categories.json");

        Optional<InputStream> isOpt = Optional.ofNullable(is);
        if (isOpt.isEmpty()) {
            return Collections.emptySet();
        }

        Type categoriesType = new TypeToken<Set<String>>() {}.getType();

        return gson.fromJson(new InputStreamReader(isOpt.get()), categoriesType);
    }
}
