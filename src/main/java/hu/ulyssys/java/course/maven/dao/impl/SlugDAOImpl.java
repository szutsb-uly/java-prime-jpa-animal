package hu.ulyssys.java.course.maven.dao.impl;

import hu.ulyssys.java.course.maven.dao.SlugDAO;
import hu.ulyssys.java.course.maven.entity.Slug;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class SlugDAOImpl implements SlugDAO {

    @PersistenceContext(unitName = "TestPersistence")
    private EntityManager entityManager;

    @Override
    public List<Slug> findAll() {
        return entityManager.createQuery("select n from Slug n order by n.id", Slug.class).getResultList();
    }

    @Override
    public Slug findById(Long id) {
        return entityManager.find(Slug.class, id);
    }

    @Transactional
    @Override
    public Slug save(Slug entity) {
        entityManager.persist(entity);
        return entity;
    }

    @Transactional
    @Override
    public Slug update(Slug entity) {
        return entityManager.merge(entity);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        entityManager.remove(findById(id));
    }
}
