package co.inventorsoft.academy.articleanalyzer.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class Categories {
    List<String> categories;
}
