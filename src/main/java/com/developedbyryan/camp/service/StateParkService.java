package com.developedbyryan.camp.service;


import com.developedbyryan.camp.model.Photo;
import com.developedbyryan.camp.model.StatePark;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface StateParkService {
    Iterable<StatePark> findAll();
    StatePark findOne(Long id);
    void save(StatePark statePark, MultipartFile file);
    void save(StatePark statePark);
    void delete(StatePark statePark);
    Photo getMainPhoto(Long id);
}
