package com.developedbyryan.camp.service;


import com.developedbyryan.camp.dao.RoleDao;
import com.developedbyryan.camp.model.AppUser;
import com.developedbyryan.camp.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleDao roleDao;

    @Autowired
    private UserService userService;

    @Override
    public Iterable<Role> findAll() {
        return roleDao.findAll();
    }

    @Override
    public Role findOne(Long id) {
        return roleDao.findOne(id);
    }

    @Override
    public void save(Role role) {
        roleDao.save(role);
    }

    @Override
    public void delete(Role role, String currentUsername) throws IllegalArgumentException {
        AppUser currentAppUser = userService.findByUsername(currentUsername);
        Role currentUserRole = currentAppUser.getRole();

        if (!currentUserRole.getName().equals(role.getName())) {
            roleDao.delete(role);
        } else {
            throw new IllegalArgumentException("Cannot delete the role of the currently logged in user!");
        }
    }
}
