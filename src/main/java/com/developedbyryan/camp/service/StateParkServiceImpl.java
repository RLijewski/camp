package com.developedbyryan.camp.service;


import com.developedbyryan.camp.dao.PhotoDao;
import com.developedbyryan.camp.dao.StateParkDao;
import com.developedbyryan.camp.model.Photo;
import com.developedbyryan.camp.model.StatePark;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Random;

@Service
public class StateParkServiceImpl implements StateParkService {
    @Autowired
    private StateParkDao stateParkDao;

    @Autowired
    private PhotoService photoService;

    @Override
    public Iterable<StatePark> findAll() {

        return stateParkDao.findAll();
    }

    @Override
    public StatePark findOne(Long id) {

        return stateParkDao.findOne(id);
    }

    @Override
    public void save(StatePark statePark, MultipartFile file) {
        Photo newPhoto = new Photo();

        StatePark newStatePark = stateParkDao.save(statePark);

        newPhoto.setStatePark(newStatePark);
        newPhoto.setMainPhoto(true);

        photoService.save(newPhoto, file);
    }

    @Override
    public void save(StatePark statePark) {
        stateParkDao.save(statePark);
    }

    @Override
    public void delete(StatePark statePark) {

        stateParkDao.delete(statePark);
    }

    @Override
    public byte[] getMainPhoto(Long id) {
        StatePark statePark = stateParkDao.findOne(id);
        List<Photo> allPhotos = statePark.getPhotos();

        for (Photo photo : allPhotos) {
            if (photo.isMainPhoto()) {
                return photo.getImage();
            }
        }

        return null;
    }
}
