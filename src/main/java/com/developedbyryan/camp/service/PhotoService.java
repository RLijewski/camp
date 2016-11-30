package com.developedbyryan.camp.service;


import com.developedbyryan.camp.model.Photo;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface PhotoService {
    Iterable<Photo> findAll();
    Photo findOne(Long id);
    void save(Photo photo, MultipartFile file);
    void delete(Photo photo) throws Exception;
    void save(Photo photo);
}
