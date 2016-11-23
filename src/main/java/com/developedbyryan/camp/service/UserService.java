package com.developedbyryan.camp.service;


import com.developedbyryan.camp.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    Iterable<User> findAll();
    User findOne(Long id);
    User findByUsername(String username);
    void save(User user);
    void delete(User user);
}
