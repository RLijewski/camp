package com.developedbyryan.camp.dao;

import com.developedbyryan.camp.model.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleDao extends CrudRepository<Role, Long> {

}
