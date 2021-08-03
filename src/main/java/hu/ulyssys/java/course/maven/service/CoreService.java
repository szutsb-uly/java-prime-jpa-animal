package hu.ulyssys.java.course.maven.service;

import hu.ulyssys.java.course.maven.entity.AbstractAnimal;

import java.util.List;

public interface CoreService<T extends AbstractAnimal> {
    List<T> getAll();

    void add(T animal);

    void remove(T animal);

    void update(T animal);
}
