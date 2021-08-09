package hu.ulyssys.java.course.maven.mbean;

import hu.ulyssys.java.course.maven.entity.AppUser;
import hu.ulyssys.java.course.maven.entity.Farmer;
import hu.ulyssys.java.course.maven.service.AppUserService;
import hu.ulyssys.java.course.maven.service.FarmerService;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.UUID;

@Named
@ViewScoped
public class AppUserCRUDMbean extends CoreCRUDMbean<AppUser> implements Serializable {


    @Inject
    public AppUserCRUDMbean(AppUserService userService) {
        super(userService);

    }


    @Override
    protected String dialogName() {
        return "appUserDialog";
    }


    @Override
    public void save() {
        if (getSelectedEntity().getId() == null) {
            //TODO hashelés
            getSelectedEntity().setPasswordHash(getSelectedEntity().getPasswordHash());
        }
        super.save();
    }

    @Override
    protected AppUser initNewEntity() {
        return new AppUser();
    }
}
