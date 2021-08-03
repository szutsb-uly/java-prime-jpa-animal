package hu.ulyssys.java.course.maven.service.impl;

import hu.ulyssys.java.course.maven.dao.FarmerDAO;
import hu.ulyssys.java.course.maven.entity.Farmer;
import hu.ulyssys.java.course.maven.service.FarmerService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class FarmerServiceImpl implements FarmerService {

    @Inject
    private FarmerDAO dao;

    @Override
    public List<Farmer> getAll() {
        return dao.findAll();
    }

    @Transactional
    @Override
    public void add(Farmer farmer) {
        dao.save(farmer);
    }

    @Transactional
    @Override
    public void remove(Farmer farmer) {
        dao.delete(farmer.getId());

    }

    @Transactional
    @Override
    public void update(Farmer farmer) {
        dao.update(farmer);
    }
}
