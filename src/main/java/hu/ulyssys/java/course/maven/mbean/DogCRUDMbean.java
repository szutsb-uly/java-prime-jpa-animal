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
public class DogCRUDMbean implements Serializable {
    private List<Farmer> farmerList;

    private List<Dog> list;
    private Dog selectedDog;
    private boolean inFunction;

    private DogService dogService;

    @Inject
    public DogCRUDMbean(DogService dogService, FarmerService farmerService) {
        this.dogService = dogService;
        list = dogService.getAll();
        farmerList = farmerService.getAll();

        selectedDog = new Dog();
    }

    public void initSave() {
        selectedDog = new Dog();
        inFunction = true;
    }

    public void initEdit() {
        inFunction = true;
    }

    public void save() {
        if (selectedDog.getId() == null) {
            dogService.add(selectedDog);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Sikeres hozzáadás! név:" + selectedDog.getName()));
            list = dogService.getAll();
            selectedDog = new Dog();

        } else {
            dogService.update(selectedDog);
            list = dogService.getAll();
            selectedDog = new Dog();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Sikeres módosítás"));
        }
        inFunction = false;
    }

    public void remove() {
        dogService.remove(selectedDog);
        list = dogService.getAll();
        inFunction = false;

        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Sikeres törlés"));

    }

    public List<Dog> getList() {
        return list;
    }

    public void setList(List<Dog> list) {
        this.list = list;
    }

    public Dog getSelectedDog() {
        return selectedDog;
    }

    public void setSelectedDog(Dog selectedDog) {
        this.selectedDog = selectedDog;
    }

    public boolean isInFunction() {
        return inFunction;
    }

    public List<Farmer> getFarmerList() {
        return farmerList;
    }

    public void setFarmerList(List<Farmer> farmerList) {
        this.farmerList = farmerList;
    }
}
