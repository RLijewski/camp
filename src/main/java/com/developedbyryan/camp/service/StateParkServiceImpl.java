package com.developedbyryan.camp.service;


import com.developedbyryan.camp.dao.StateParkDao;
import com.developedbyryan.camp.model.StatePark;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class StateParkServiceImpl implements StateParkService {
    @Autowired
    private StateParkDao stateParkDao;

    @Override
    public List<StatePark> findAll() {

        return stateParkDao.findAll();
    }

    @Override
    public StatePark findById(Long id) {

        return stateParkDao.findById(id);
    }

    @Override
    public void save(StatePark statePark, MultipartFile file) {
        try {
            statePark.setBytes(file.getBytes());
            stateParkDao.save(statePark);
        } catch (IOException e) {
            System.err.println("Unable to get byte array from uploaded file");
        }
    }

    @Override
    public void delete(StatePark statePark) {
        stateParkDao.delete(statePark);
    }
}
