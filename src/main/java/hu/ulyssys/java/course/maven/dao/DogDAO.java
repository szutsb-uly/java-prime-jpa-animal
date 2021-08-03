package hu.ulyssys.java.course.maven.dao;

import hu.ulyssys.java.course.maven.entity.Dog;
import hu.ulyssys.java.course.maven.entity.Farmer;

import java.util.List;

public interface DogDAO {

    List<Dog> findAll();

    Dog findById(Long id);

    Dog save(Dog entity);

    Dog update(Dog entity);

    void delete(Long id);

}
