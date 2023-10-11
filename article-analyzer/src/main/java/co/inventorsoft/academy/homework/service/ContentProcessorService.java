package co.inventorsoft.academy.homework.service;

import co.inventorsoft.academy.homework.model.Article;

import java.util.List;
import java.util.Set;

public interface ContentProcessorService {
    Set<String> process(List<Article> articles);

}
