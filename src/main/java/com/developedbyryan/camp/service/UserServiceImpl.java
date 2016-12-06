package com.developedbyryan.camp.service;


import com.developedbyryan.camp.dao.UserDao;
import com.developedbyryan.camp.model.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public Iterable<AppUser> findAll() {

        return userDao.findAll();
    }

    @Override
    public AppUser findOne(Long id) {
        return userDao.findOne(id);
    }

    @Override
    public AppUser findByUsername(String username) {
        return userDao.findByUsername(username);
    }

    @Override
    public void save(AppUser appUser) {
        userDao.save(appUser);
    }

    @Override
    public void delete(AppUser appUser, String currentUsername) throws IllegalArgumentException {
        if (!appUser.getUsername().equals(currentUsername)) {
            userDao.delete(appUser);
        } else {
            throw new IllegalArgumentException("Cannot delete the currently logged in appUser!");
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Load appUser from the database (throw exception if not found)
        AppUser appUser = userDao.findByUsername(username);
        if(appUser == null) {
            throw new UsernameNotFoundException("AppUser not found");
        }

        // Return appUser object
        return appUser;
    }
}
