package com.developedbyryan.camp.dao;


import com.developedbyryan.camp.model.StatePark;

import java.util.List;

public interface StateParkDao {
    List<StatePark> findAll();
    StatePark findById(Long id);
    void save(StatePark statePark);
    void delete(StatePark statePark);
}
