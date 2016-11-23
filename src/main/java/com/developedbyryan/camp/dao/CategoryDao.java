package com.developedbyryan.camp.dao;


import com.developedbyryan.camp.model.Category;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryDao extends CrudRepository<Category, Long> {

}
