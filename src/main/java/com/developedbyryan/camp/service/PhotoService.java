package com.developedbyryan.camp.service;


import com.developedbyryan.camp.model.Photo;

import java.util.List;

public interface PhotoService {
    Iterable<Photo> findAll();
    Photo findOne(Long id);
    void save(Photo photo);
    void delete(Photo photo);
}
