package co.inventorsoft.academy.articleanalyzer.repository;

import co.inventorsoft.academy.articleanalyzer.model.Categories;
import co.inventorsoft.academy.articleanalyzer.service.jsonmanager.JsonManagerService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
public class CategoryRepository {

    private final JsonManagerService<Categories> jsonManagerService;
    private final String categoriesPath;

    public void saveCategoriesToJSONFileAsArray(List<String> categories) {
        jsonManagerService.save(categories, categoriesPath);
    }
}
