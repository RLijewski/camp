package com.developedbyryan.camp.service;


import com.developedbyryan.camp.model.AppUser;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    Iterable<AppUser> findAll();
    AppUser findOne(Long id);
    AppUser findByUsername(String username);
    void save(AppUser appUser);
    void delete(AppUser appUser, String currentUsername) throws IllegalArgumentException;
}
