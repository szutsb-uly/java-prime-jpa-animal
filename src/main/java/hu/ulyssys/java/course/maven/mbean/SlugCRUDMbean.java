package hu.ulyssys.java.course.maven.mbean;

import hu.ulyssys.java.course.maven.entity.Farmer;
import hu.ulyssys.java.course.maven.entity.Slug;
import hu.ulyssys.java.course.maven.service.FarmerService;
import hu.ulyssys.java.course.maven.service.SlugService;
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
public class SlugCRUDMbean implements Serializable {
    private List<Farmer> farmerList;

    private List<Slug> list;
    private Slug selectedSlug;

    private SlugService slugService;

    @Inject
    public SlugCRUDMbean(SlugService slugService, FarmerService farmerService) {
        this.slugService = slugService;
        list = slugService.getAll();
        farmerList = farmerService.getAll();
        selectedSlug = new Slug();
    }

    public void initSave() {
        selectedSlug = new Slug();
    }

    public void save() {
        if (selectedSlug.getId() == null) {
            slugService.add(selectedSlug);
            list = slugService.getAll();
            selectedSlug = new Slug();
                FacesContext.getCurrentInstance().addMessage(null,new FacesMessage("Sikeres mentés"));

        } else {
            slugService.update(selectedSlug);
            list = slugService.getAll();
            selectedSlug = new Slug();
            FacesContext.getCurrentInstance().addMessage(null,new FacesMessage("Sikeres módosítás"));
        }
        PrimeFaces.current().executeScript("PF('slugDialog').hide()");
    }

    public void remove() {
        slugService.remove(selectedSlug);
        list = slugService.getAll();
        FacesContext.getCurrentInstance().addMessage(null,new FacesMessage("Sikeres törlés"));

    }

    public List<Slug> getList() {
        return list;
    }

    public void setList(List<Slug> list) {
        this.list = list;
    }

    public Slug getSelectedSlug() {
        return selectedSlug;
    }

    public void setSelectedSlug(Slug selectedSlug) {
        this.selectedSlug = selectedSlug;
    }

    public List<Farmer> getFarmerList() {
        return farmerList;
    }

    public void setFarmerList(List<Farmer> farmerList) {
        this.farmerList = farmerList;
    }
}
