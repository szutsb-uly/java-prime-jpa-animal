package hu.ulyssys.java.course.maven.mbean;

import hu.ulyssys.java.course.maven.entity.Dog;
import hu.ulyssys.java.course.maven.entity.Farmer;
import hu.ulyssys.java.course.maven.service.DogService;
import hu.ulyssys.java.course.maven.service.FarmerService;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class DogCRUDMbean extends FarmerAwareCRUDMBean<Dog> implements Serializable {


    @Inject
    public DogCRUDMbean(DogService dogService, FarmerService farmerService) {
        super(dogService, farmerService);
    }


    @Override
    protected String dialogName() {
        return "dogDialog";
    }

    @Override
    protected Dog initNewEntity() {
        return new Dog();
    }
}
