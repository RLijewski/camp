package com.developedbyryan.camp.dao;


import com.developedbyryan.camp.model.StatePark;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StateParkDaoImpl implements StateParkDao {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    @SuppressWarnings("unchecked")
    public List<StatePark> findAll() {
        // Open Session
        Session session = sessionFactory.openSession();

        // Get all State Parks with a hibernate criteria
        List<StatePark> stateParks = session.createCriteria(StatePark.class).list();

        // Close hibernate session
        session.close();

        return stateParks;
    }

    @Override
    public StatePark findById(Long id) {
        // Open Session
        Session session = sessionFactory.openSession();

        StatePark statePark = session.get(StatePark.class, id);

        // Close hibernate session
        session.close();

        return statePark;
    }

    @Override
    public void save(StatePark statePark) {
        // Open Session
        Session session = sessionFactory.openSession();

        // Begin transaction
        session.beginTransaction();

        // Save the State Park
        session.save(statePark);

        // Commit the Transaction
        session.getTransaction().commit();

        // Close the session
        session.close();
    }

    @Override
    public void delete(StatePark statePark) {
        // Open Session
        Session session = sessionFactory.openSession();

        // Begin transaction
        session.beginTransaction();

        // Save the State Park
        session.delete(statePark);

        // Commit the Transaction
        session.getTransaction().commit();

        // Close hibernate session
        session.close();
    }
}
