package com.developedbyryan.camp.service;

import com.developedbyryan.camp.model.Category;

import java.util.List;

public interface CategoryService {
    Iterable<Category> findAll();
    Category findOne(Long id);
    void save(Category category);
    void delete(Category category);
}
