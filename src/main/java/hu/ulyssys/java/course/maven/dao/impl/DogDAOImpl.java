package hu.ulyssys.java.course.maven.dao.impl;

import hu.ulyssys.java.course.maven.dao.DogDAO;
import hu.ulyssys.java.course.maven.entity.Dog;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class DogDAOImpl extends CoreDAOImpl<Dog> implements DogDAO {

    @Override
    protected Class<Dog> getManagedClass() {
        return Dog.class;
    }
}
