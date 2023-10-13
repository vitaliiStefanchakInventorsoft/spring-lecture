package co.inventorsoft.academy.articleanalyzer.repository;

import co.inventorsoft.academy.articleanalyzer.model.Categories;
import co.inventorsoft.academy.articleanalyzer.service.jsonmanager.JsonManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CategoryRepository {
    @Autowired
    JsonManagerService<Categories> jsonManagerService;
    @Value("${categories.path}")
    private String categoriesPath;

    public void saveCategoriesToJSONFileAsArray(List<String> categories, String fileName) {
        jsonManagerService.save(categories, categoriesPath, fileName);
    }

    public void saveCategoriesToJSONFileAsObject(List<String> categories, String fileName) {
        Categories categoriesObject = new Categories(categories);
        jsonManagerService.save(categoriesObject, categoriesPath, fileName);
    }
}
