package hu.ulyssys.java.course.maven.dao.impl;

import hu.ulyssys.java.course.maven.dao.SlugDAO;
import hu.ulyssys.java.course.maven.entity.Slug;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class SlugDAOImpl extends CoreDAOImpl<Slug> implements SlugDAO {

    @Override
    protected Class<Slug> getManagedClass() {
        return Slug.class;
    }
}
