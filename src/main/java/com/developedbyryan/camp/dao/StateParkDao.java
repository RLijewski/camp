package com.developedbyryan.camp.dao;


import com.developedbyryan.camp.model.StatePark;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StateParkDao extends CrudRepository<StatePark, Long> {

}
