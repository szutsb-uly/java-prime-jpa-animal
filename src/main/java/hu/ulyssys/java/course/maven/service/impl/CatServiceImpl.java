package hu.ulyssys.java.course.maven.service.impl;

import hu.ulyssys.java.course.maven.dao.CatDAO;
import hu.ulyssys.java.course.maven.entity.Cat;
import hu.ulyssys.java.course.maven.service.CatService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class CatServiceImpl implements CatService {

    @Inject
    private CatDAO dao;

    @Override
    public List<Cat> getAll() {
        return dao.findAll();
    }

    @Transactional
    @Override
    public void add(Cat animal) {
        dao.save(animal);
    }

    @Transactional
    @Override
    public void remove(Cat animal) {
        dao.delete(animal.getId());

    }

    @Transactional
    @Override
    public void update(Cat animal) {
        dao.update(animal);
    }
}
