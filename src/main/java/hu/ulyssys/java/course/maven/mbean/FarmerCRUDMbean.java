package hu.ulyssys.java.course.maven.mbean;

import hu.ulyssys.java.course.maven.entity.Farmer;
import hu.ulyssys.java.course.maven.service.FarmerService;
import org.primefaces.PrimeFaces;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class FarmerCRUDMbean implements Serializable {

    private List<Farmer> list;
    private Farmer selectedFarmer;

    private FarmerService farmerService;

    @Inject
    public FarmerCRUDMbean(FarmerService farmerService) {
        this.farmerService = farmerService;
        list = farmerService.getAll();
        selectedFarmer = new Farmer();
    }

    public void initSave() {
        selectedFarmer = new Farmer();
    }

    public void save() {
        if (selectedFarmer.getId() == null) {
            farmerService.add(selectedFarmer);
            list = farmerService.getAll();
            selectedFarmer = new Farmer();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Sikeres mentés"));

        } else {
            farmerService.update(selectedFarmer);
            list = farmerService.getAll();
            selectedFarmer = new Farmer();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Sikeres módosítás"));
        }
        PrimeFaces.current().executeScript("PF('farmerDialog').hide()");
    }

    public void remove() {
        farmerService.remove(selectedFarmer);
        list = farmerService.getAll();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Sikeres törlés"));

    }

    public List<Farmer> getList() {
        return list;
    }

    public void setList(List<Farmer> list) {
        this.list = list;
    }

    public Farmer getSelectedFarmer() {
        return selectedFarmer;
    }

    public void setSelectedFarmer(Farmer selectedFarmer) {
        this.selectedFarmer = selectedFarmer;
    }

}
