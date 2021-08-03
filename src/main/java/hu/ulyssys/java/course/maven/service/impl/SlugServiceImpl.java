package hu.ulyssys.java.course.maven.service.impl;

import hu.ulyssys.java.course.maven.dao.SlugDAO;
import hu.ulyssys.java.course.maven.entity.Slug;
import hu.ulyssys.java.course.maven.service.SlugService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class SlugServiceImpl implements SlugService {

    @Inject
    private SlugDAO dao;

    @Override
    public List<Slug> getAll() {
        return dao.findAll();
    }

    @Transactional
    @Override
    public void add(Slug animal) {
        dao.save(animal);
    }

    @Transactional
    @Override
    public void remove(Slug animal) {
        dao.delete(animal.getId());

    }

    @Transactional
    @Override
    public void update(Slug animal) {
        dao.update(animal);
    }
}
