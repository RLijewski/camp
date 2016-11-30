package com.developedbyryan.camp.service;


import com.developedbyryan.camp.model.Role;

public interface RoleService {
    Iterable<Role> findAll();
    Role findOne(Long id);
    void save(Role role);
    void delete(Role role, String currentUsername);
}
