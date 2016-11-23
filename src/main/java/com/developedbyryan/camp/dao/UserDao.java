package com.developedbyryan.camp.dao;


import com.developedbyryan.camp.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDao extends CrudRepository<User,Long> {
    User findByUsername(String username);
}
