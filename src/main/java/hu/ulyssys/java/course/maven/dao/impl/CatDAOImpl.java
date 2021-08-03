package hu.ulyssys.java.course.maven.dao.impl;

import hu.ulyssys.java.course.maven.dao.CatDAO;
import hu.ulyssys.java.course.maven.entity.Cat;

import javax.ejb.Stateless;

@Stateless
public class CatDAOImpl extends CoreDAOImpl<Cat> implements CatDAO {

    @Override
    protected Class<Cat> getManagedClass() {
        return Cat.class;
    }
}
