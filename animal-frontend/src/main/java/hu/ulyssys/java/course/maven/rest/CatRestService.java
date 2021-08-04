package hu.ulyssys.java.course.maven.rest;

import hu.ulyssys.java.course.maven.entity.Cat;
import hu.ulyssys.java.course.maven.rest.model.CatModel;
import hu.ulyssys.java.course.maven.service.CatService;
import hu.ulyssys.java.course.maven.service.FarmerService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.stream.Collectors;

@Path("/cat")
public class CatRestService {
    @Inject
    private FarmerService farmerService;
    @Inject
    private CatService catService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() {
        return Response.ok(catService.getAll().stream().map(this::createModelFromEntity).collect(Collectors.toList())).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response save(CatModel model) {
        Cat cat = new Cat();
        cat.setId(model.getId());
        cat.setLegsNumber(model.getLegsNumber());
        if (model.getFarmerID() != null) {
            cat.setFarmer(farmerService.findById(model.getFarmerID()));
        }
        catService.add(cat);
        return Response.ok(createModelFromEntity(cat)).build();
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(CatModel model) {
        Cat cat = catService.findById(model.getId());
        if (cat == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        if (model.getFarmerID() != null) {
            cat.setFarmer(farmerService.findById(model.getFarmerID()));
        }
        cat.setName(model.getName());
        cat.setLegsNumber(model.getLegsNumber());
        catService.update(cat);
        return Response.ok(createModelFromEntity(cat)).build();

    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("id") Long id) {
        Cat cat = catService.findById(id);
        if (cat == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        catService.remove(cat);
        return Response.ok().build();
    }

    private CatModel createModelFromEntity(Cat cat) {
        CatModel model = new CatModel();
        model.setLegsNumber(cat.getLegsNumber());
        model.setId(cat.getId());
        model.setName(cat.getName());
        if (cat.getFarmer() != null) {
            model.setFarmerID(cat.getFarmer().getId());
        }
        return model;
    }
}
