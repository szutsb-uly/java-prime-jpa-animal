package hu.ulyssys.java.course.maven.rest;

import hu.ulyssys.java.course.maven.entity.Dog;
import hu.ulyssys.java.course.maven.rest.model.DogModel;
import hu.ulyssys.java.course.maven.service.DogService;
import hu.ulyssys.java.course.maven.service.FarmerService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.stream.Collectors;

@Path("/dog")
public class DogRestService {
    @Inject
    private FarmerService farmerService;
    @Inject
    private DogService service;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() {
        return Response.ok(service.getAll().stream().map(this::createModelFromEntity).collect(Collectors.toList())).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response save(DogModel model) {
        Dog Dog = new Dog();
        Dog.setId(model.getId());
        Dog.setLegsNumber(model.getLegsNumber());
        if (model.getFarmerID() != null) {
            Dog.setFarmer(farmerService.findById(model.getFarmerID()));
        }
        service.add(Dog);
        return Response.ok(createModelFromEntity(Dog)).build();
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(DogModel model) {
        Dog Dog = service.findById(model.getId());
        if (Dog == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        if (model.getFarmerID() != null) {
            Dog.setFarmer(farmerService.findById(model.getFarmerID()));
        }
        Dog.setName(model.getName());
        Dog.setLegsNumber(model.getLegsNumber());
        service.update(Dog);
        return Response.ok(createModelFromEntity(Dog)).build();

    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("id") Long id) {
        Dog Dog = service.findById(id);
        if (Dog == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        service.remove(Dog);
        return Response.ok().build();
    }

    private DogModel createModelFromEntity(Dog dog) {
        DogModel model = new DogModel();
        model.setLegsNumber(dog.getLegsNumber());
        model.setId(dog.getId());
        model.setName(dog.getName());
        if (dog.getFarmer() != null) {
            model.setFarmerID(dog.getFarmer().getId());
        }
        return model;
    }
}
