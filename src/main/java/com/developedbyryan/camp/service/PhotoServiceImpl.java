package com.developedbyryan.camp.service;


import com.developedbyryan.camp.dao.PhotoDao;
import com.developedbyryan.camp.model.Photo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PhotoServiceImpl implements PhotoService {
    @Autowired
    private PhotoDao photoDao;

    @Override
    public Iterable<Photo> findAll() {
        return photoDao.findAll();
    }

    @Override
    public Photo findOne(Long id) {
        return photoDao.findOne(id);
    }

    @Override
    public void save(Photo photo) {
        photoDao.save(photo);
    }

    @Override
    public void delete(Photo photo) {
        photoDao.delete(photo);
    }
}
