package hu.ulyssys.java.course.maven.service.impl;

import hu.ulyssys.java.course.maven.entity.AbstractAnimal;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractServiceImpl<T extends AbstractAnimal> {
    private List<T> list = new ArrayList<>();

    public List<T> getAll() {
        return list;
    }

    public void add(T animal) {
        list.add(animal);
    }

    public void remove(T animal) {
        list.remove(animal);
    }

}
