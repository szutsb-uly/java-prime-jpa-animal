package hu.ulyssys.java.course.maven.mbean;

import hu.ulyssys.java.course.maven.entity.MenuItem;
import hu.ulyssys.java.course.maven.service.MenuItemService;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@Named
@ViewScoped
public class MenuCRUDMbean extends CoreCRUDMbean<MenuItem> implements Serializable {


    @Inject
    public MenuCRUDMbean(MenuItemService menuItemService) {
        super(menuItemService);

    }


    @Override
    protected String dialogName() {
        return "menuDialog";
    }

    @Override
    protected MenuItem initNewEntity() {
        return new MenuItem();
    }
}
