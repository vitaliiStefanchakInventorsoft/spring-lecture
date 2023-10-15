package co.inventorsoft.academy.articleanalyzer.repository;

import co.inventorsoft.academy.articleanalyzer.model.Categories;
import co.inventorsoft.academy.articleanalyzer.service.jsonmanager.JsonManagerService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CategoryRepository {

    private final JsonManagerService<Categories> jsonManagerService;

    @Value("${categories.path}")
    private String categoriesPath;

    public CategoryRepository(JsonManagerService<Categories> jsonManagerService) {
        this.jsonManagerService = jsonManagerService;
    }

    public void saveCategoriesToJSONFileAsArray(List<String> categories) {
        jsonManagerService.save(categories, categoriesPath);
    }
}
