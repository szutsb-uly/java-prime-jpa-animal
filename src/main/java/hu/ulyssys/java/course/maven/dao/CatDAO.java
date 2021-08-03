package hu.ulyssys.java.course.maven.dao;

import hu.ulyssys.java.course.maven.entity.Cat;
import hu.ulyssys.java.course.maven.entity.Farmer;

import java.util.List;

public interface CatDAO {

    List<Cat> findAll();

    Cat findById(Long id);

    Cat save(Cat entity);

    Cat update(Cat entity);

    void delete(Long id);

}
