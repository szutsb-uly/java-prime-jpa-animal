package hu.ulyssys.java.course.maven.dao.impl;

import hu.ulyssys.java.course.maven.dao.FarmerDAO;
import hu.ulyssys.java.course.maven.entity.Farmer;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class FarmerDAOImpl extends CoreDAOImpl<Farmer> implements FarmerDAO {


    @Override
    public Farmer findByName(String name) {
        //TODO CriteryQuery
        return null;
    }

    @Override
    protected Class<Farmer> getManagedClass() {
        return Farmer.class;
    }
}
