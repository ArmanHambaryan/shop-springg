package am.itspace.shopspring.service;

import am.itspace.shopspring.model.Category;

import java.util.List;

public interface CategoryService {

    List<Category> findAll();

    void save(Category category);

    void deleteById(Integer id);

}
