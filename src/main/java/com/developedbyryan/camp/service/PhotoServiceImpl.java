package com.developedbyryan.camp.service;


import com.developedbyryan.camp.dao.PhotoDao;
import com.developedbyryan.camp.model.Photo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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
    public void save(Photo photo, MultipartFile file) {
        try {
            photo.setImage(file.getBytes());
        } catch (IOException e) {
            System.err.println("Unable to get byte array from uploaded file.");
        }
        photoDao.save(photo);
    }

    @Override
    public void save(Photo photo) {
        Photo existingPhoto = photoDao.findOne(photo.getId());
        if (existingPhoto.getImage() != null) {
            photo.setImage(existingPhoto.getImage());
        }

        if (existingPhoto.isMainPhoto()) {
            photo.setMainPhoto(true);
        }

        photoDao.save(photo);
    }

    @Override
    public void delete(Photo photo) throws Exception {
        if (!photo.isMainPhoto()) {
            photoDao.delete(photo);
        } else {
            throw new Exception("This photo is the main photo for a state park and cannot be deleted!");
        }
    }
}
