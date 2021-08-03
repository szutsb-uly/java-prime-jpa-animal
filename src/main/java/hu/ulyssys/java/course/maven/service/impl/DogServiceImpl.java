package hu.ulyssys.java.course.maven.service.impl;

import hu.ulyssys.java.course.maven.dao.DogDAO;
import hu.ulyssys.java.course.maven.entity.Dog;
import hu.ulyssys.java.course.maven.service.DogService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class DogServiceImpl  implements DogService {

    @Inject
    private DogDAO dao;

    @Override
    public List<Dog> getAll() {
        return dao.findAll();
    }

    @Transactional
    @Override
    public void add(Dog animal) {
        dao.save(animal);
    }

    @Transactional
    @Override
    public void remove(Dog animal) {
        dao.delete(animal.getId());

    }

    @Transactional
    @Override
    public void update(Dog animal) {
        dao.update(animal);
    }
}
