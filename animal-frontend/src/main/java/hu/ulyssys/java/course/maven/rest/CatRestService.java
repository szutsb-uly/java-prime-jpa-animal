package hu.ulyssys.java.course.maven.rest;

import hu.ulyssys.java.course.maven.entity.Cat;
import hu.ulyssys.java.course.maven.rest.model.CatModel;
import hu.ulyssys.java.course.maven.service.CatService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.stream.Collectors;

@Path("/cat")
public class CatRestService extends CoreRestService<Cat, CatModel> {





    @Override
    protected Cat initNewEntity() {
        return new Cat();
    }

    @Override
    protected CatModel initNewModel() {
        return new CatModel();
    }
}
