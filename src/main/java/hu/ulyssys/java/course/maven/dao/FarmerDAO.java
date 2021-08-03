package hu.ulyssys.java.course.maven.dao;

import hu.ulyssys.java.course.maven.entity.Farmer;

import java.util.List;

public interface FarmerDAO {

    List<Farmer> findAll();

    Farmer findById(Long id);

    Farmer save(Farmer entity);

    Farmer update(Farmer entity);

    void delete(Long id);

}
