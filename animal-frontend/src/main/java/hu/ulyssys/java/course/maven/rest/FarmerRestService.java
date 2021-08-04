package hu.ulyssys.java.course.maven.rest;

import hu.ulyssys.java.course.maven.entity.Farmer;
import hu.ulyssys.java.course.maven.rest.model.FarmerModel;
import hu.ulyssys.java.course.maven.service.FarmerService;
import hu.ulyssys.java.course.maven.service.FarmerService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.stream.Collectors;

@Path("/farmer")
public class FarmerRestService {

    @Inject
    private FarmerService service;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() {
        return Response.ok(service.getAll().stream().map(this::createModelFromEntity).collect(Collectors.toList())).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response save(FarmerModel model) {
        Farmer farmer = new Farmer();
        farmer.setId(model.getId());
        farmer.setFullName(model.getFullName());

        service.add(farmer);
        return Response.ok(createModelFromEntity(farmer)).build();
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(FarmerModel model) {
        Farmer farmer = service.findById(model.getId());
        if (farmer == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        farmer.setFullName(model.getFullName());
        service.update(farmer);
        return Response.ok(createModelFromEntity(farmer)).build();

    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("id") Long id) {
        Farmer Farmer = service.findById(id);
        if (Farmer == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        service.remove(Farmer);
        return Response.ok().build();
    }

    private FarmerModel createModelFromEntity(Farmer farmer) {
        FarmerModel model = new FarmerModel();
        model.setId(farmer.getId());
        model.setFullName(farmer.getFullName());

        return model;
    }
}
