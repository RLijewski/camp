package com.developedbyryan.camp.service;


import com.developedbyryan.camp.model.StatePark;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface StateParkService {
    List<StatePark> findAll();
    StatePark findById(Long id);
    void save(StatePark statePark, MultipartFile file);
    void delete(StatePark statePark);
}
