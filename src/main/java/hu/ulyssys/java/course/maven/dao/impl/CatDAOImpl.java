package hu.ulyssys.java.course.maven.dao.impl;

import hu.ulyssys.java.course.maven.dao.CatDAO;
import hu.ulyssys.java.course.maven.dao.DogDAO;
import hu.ulyssys.java.course.maven.entity.Cat;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class CatDAOImpl implements CatDAO {

    @PersistenceContext(unitName = "TestPersistence")
    private EntityManager entityManager;

    @Override
    public List<Cat> findAll() {
        return entityManager.createQuery("select n from Cat n order by n.id", Cat.class).getResultList();
    }

    @Override
    public Cat findById(Long id) {
        return entityManager.find(Cat.class, id);
    }

    @Transactional
    @Override
    public Cat save(Cat entity) {
        entityManager.persist(entity);
        return entity;
    }

    @Transactional
    @Override
    public Cat update(Cat entity) {
        return entityManager.merge(entity);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        entityManager.remove(findById(id));
    }
}
