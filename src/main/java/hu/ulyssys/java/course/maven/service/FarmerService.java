package hu.ulyssys.java.course.maven.service;

import hu.ulyssys.java.course.maven.entity.Farmer;

import java.util.List;

public interface FarmerService {

    List<Farmer> getAll();

    void add(Farmer entity);

    void remove(Farmer entity);

    void update(Farmer entity);
}
