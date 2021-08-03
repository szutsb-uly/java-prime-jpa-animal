package hu.ulyssys.java.course.maven.dao.impl;

import hu.ulyssys.java.course.maven.dao.FarmerDAO;
import hu.ulyssys.java.course.maven.entity.Farmer;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class FarmerDAOImpl implements FarmerDAO {

    @PersistenceContext(unitName = "TestPersistence")
    private EntityManager entityManager;

    @Override
    public List<Farmer> findAll() {
        return entityManager.createQuery("select n from Farmer n order by n.id", Farmer.class).getResultList();
    }

    @Override
    public Farmer findById(Long id) {
        return entityManager.find(Farmer.class, id);
    }

    @Transactional
    @Override
    public Farmer save(Farmer entity) {
        entityManager.persist(entity);
        return entity;
    }

    @Transactional
    @Override
    public Farmer update(Farmer entity) {
        return entityManager.merge(entity);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        entityManager.remove(findById(id));
    }
}
