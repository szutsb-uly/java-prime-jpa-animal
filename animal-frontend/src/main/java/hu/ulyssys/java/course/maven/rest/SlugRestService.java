package hu.ulyssys.java.course.maven.rest;

import hu.ulyssys.java.course.maven.entity.Slug;
import hu.ulyssys.java.course.maven.rest.model.SlugModel;
import hu.ulyssys.java.course.maven.service.SlugService;
import hu.ulyssys.java.course.maven.service.FarmerService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.stream.Collectors;

@Path("/slug")
public class SlugRestService {
    @Inject
    private FarmerService farmerService;
    @Inject
    private SlugService service;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() {
        return Response.ok(service.getAll().stream().map(this::createModelFromEntity).collect(Collectors.toList())).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response save(SlugModel model) {
        Slug slug = new Slug();
        slug.setId(model.getId());
        slug.setLegsNumber(model.getLegsNumber());
        if (model.getFarmerID() != null) {
            slug.setFarmer(farmerService.findById(model.getFarmerID()));
        }
        service.add(slug);
        return Response.ok(createModelFromEntity(slug)).build();
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(SlugModel model) {
        Slug slug = service.findById(model.getId());
        if (slug == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        if (model.getFarmerID() != null) {
            slug.setFarmer(farmerService.findById(model.getFarmerID()));
        }
        slug.setName(model.getName());
        slug.setLegsNumber(model.getLegsNumber());
        service.update(slug);
        return Response.ok(createModelFromEntity(slug)).build();

    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("id") Long id) {
        Slug slug = service.findById(id);
        if (slug == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        service.remove(slug);
        return Response.ok().build();
    }

    private SlugModel createModelFromEntity(Slug slug) {
        SlugModel model = new SlugModel();
        model.setLegsNumber(slug.getLegsNumber());
        model.setId(slug.getId());
        model.setName(slug.getName());
        if (slug.getFarmer() != null) {
            model.setFarmerID(slug.getFarmer().getId());
        }
        return model;
    }
}
