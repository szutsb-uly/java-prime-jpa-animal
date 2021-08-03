package hu.ulyssys.java.course.maven.dao.impl;

import hu.ulyssys.java.course.maven.dao.DogDAO;
import hu.ulyssys.java.course.maven.dao.FarmerDAO;
import hu.ulyssys.java.course.maven.entity.Dog;
import hu.ulyssys.java.course.maven.entity.Dog;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class DogDAOImpl implements DogDAO {

    @PersistenceContext(unitName = "TestPersistence")
    private EntityManager entityManager;

    @Override
    public List<Dog> findAll() {
        return entityManager.createQuery("select n from Dog n order by n.id", Dog.class).getResultList();
    }

    @Override
    public Dog findById(Long id) {
        return entityManager.find(Dog.class, id);
    }

    @Transactional
    @Override
    public Dog save(Dog entity) {
        entityManager.persist(entity);
        return entity;
    }

    @Transactional
    @Override
    public Dog update(Dog entity) {
        return entityManager.merge(entity);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        entityManager.remove(findById(id));
    }
}
