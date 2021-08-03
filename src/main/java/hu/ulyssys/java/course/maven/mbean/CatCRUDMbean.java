package hu.ulyssys.java.course.maven.mbean;

import hu.ulyssys.java.course.maven.entity.Cat;
import hu.ulyssys.java.course.maven.entity.Farmer;
import hu.ulyssys.java.course.maven.service.CatService;
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
public class CatCRUDMbean implements Serializable {
    private List<Farmer> farmerList;

    private List<Cat> list;
    private Cat selectedCat;

    private boolean inFunction;
    private CatService catService;

    @Inject
    public CatCRUDMbean(CatService carService, FarmerService farmerService) {
        this.catService = carService;
        list = carService.getAll();
        farmerList = farmerService.getAll();
        selectedCat = new Cat();
    }

    public void initSave() {
        selectedCat = new Cat();
        inFunction = true;
    }

    public void initEdit(Cat cat) {
        selectedCat = cat;
        inFunction = true;
    }

    public void save() {
        if (selectedCat.getId() == null) {
            catService.add(selectedCat);
            list = catService.getAll();
            selectedCat = new Cat();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Sikeres hozzáadás"));
        } else {
            catService.update(selectedCat);
            list = catService.getAll();
            selectedCat = new Cat();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Sikeres módosítás"));
        }
        inFunction = false;

    }

    public void remove(Cat cat) {
        catService.remove(cat);
        list = catService.getAll();
        inFunction = false;
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Sikeres törlés"));

    }

    public List<Cat> getList() {
        return list;
    }

    public void setList(List<Cat> list) {
        this.list = list;
    }

    public Cat getSelectedCat() {
        return selectedCat;
    }

    public void setSelectedCat(Cat selectedCat) {
        this.selectedCat = selectedCat;
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
