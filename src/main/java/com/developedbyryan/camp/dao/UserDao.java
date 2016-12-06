package com.developedbyryan.camp.dao;


import com.developedbyryan.camp.model.AppUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends CrudRepository<AppUser,Long> {
    AppUser findByUsername(String username);
}
