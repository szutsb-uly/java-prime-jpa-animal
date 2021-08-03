package hu.ulyssys.java.course.maven.dao;

import hu.ulyssys.java.course.maven.entity.Farmer;
import hu.ulyssys.java.course.maven.entity.Slug;

import java.util.List;

public interface SlugDAO {

    List<Slug> findAll();

    Slug findById(Long id);

    Slug save(Slug entity);

    Slug update(Slug entity);

    void delete(Long id);

}
