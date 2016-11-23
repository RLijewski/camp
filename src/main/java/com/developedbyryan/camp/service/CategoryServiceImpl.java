package com.developedbyryan.camp.service;


import com.developedbyryan.camp.dao.CategoryDao;
import com.developedbyryan.camp.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryDao categoryDao;


    @Override
    public Iterable<Category> findAll() {
        return categoryDao.findAll();
    }

    @Override
    public Category findOne(Long id) {

        return categoryDao.findOne(id);
    }

    @Override
    public void save(Category category) {
        categoryDao.save(category);
    }

    @Override
    public void delete(Category category) {
        categoryDao.delete(category);
    }
}
