package com.postgrestest.liveasy_intern.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;
import com.postgrestest.liveasy_intern.dao.LoadDao;
import com.postgrestest.liveasy_intern.entities.Load;

@Service
public class LoadServiceImpl implements LoadService {

    @Autowired
    private LoadDao loadDao;

    @Override
    public List<Load> getLoads() {
        return loadDao.findAll();
    }

    @Override
    public Load getLoad(UUID loadId) {
        return loadDao.findById(loadId).orElse(null);
    }

    @Override
    public Load addLoad(Load load) {
        return loadDao.save(load);
    }

    @Override
    public Load updateLoad(Load load) {
        return loadDao.save(load);
    }

    @Override
    public Load deleteLoad(UUID loadId) {
        Load entity = loadDao.findById(loadId).orElse(null);
        if (entity != null) {
            loadDao.delete(entity);
        }
        return entity;
    }

    // New method to get loads by shipperId
    @Override
    public List<Load> getLoadsByShipperId(UUID shipperId) {
        return loadDao.findByShipperId(shipperId); // Ensure this method exists in your DAO
    }
}
