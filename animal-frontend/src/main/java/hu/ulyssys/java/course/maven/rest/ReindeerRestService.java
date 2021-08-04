package hu.ulyssys.java.course.maven.rest;

import hu.ulyssys.java.course.maven.entity.Reindeer;
import hu.ulyssys.java.course.maven.rest.model.ReindeerModel;
import hu.ulyssys.java.course.maven.service.FarmerService;
import hu.ulyssys.java.course.maven.service.ReindeerService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.stream.Collectors;

@Path("/reindeer")
public class ReindeerRestService {

    @Inject
    private ReindeerService service;

    @Inject
    private FarmerService farmerService;

    @GET
    public Response findAll() {
      /*  List<Reindeer> entityList = service.getAll();
        List<ReindeerModel> modelList = new ArrayList<>();
        entityList.forEach(reindeer -> {
            modelList.add(createModelByEntity(reindeer));
        });*/
        //Java 8-as szintaxis, lambda
        return Response.ok(service.getAll().stream().map(this::createModelByEntity).collect(Collectors.toList())).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response save(ReindeerModel model) {
        Reindeer reindeer = new Reindeer();
        reindeer.setName(model.getName());
        reindeer.setLegsNumber(model.getLegsNumber());
        reindeer.setHornNumber(model.getHornNumber());
        if (model.getFarmerID() != null) {
            reindeer.setFarmer(farmerService.findById(model.getFarmerID()));
        }
        service.add(reindeer);

        return Response.ok(createModelByEntity(reindeer)).build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(ReindeerModel model) {
        Reindeer reindeer = service.findById(model.getId());
        if (reindeer == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        reindeer.setName(model.getName());
        reindeer.setLegsNumber(model.getLegsNumber());
        reindeer.setHornNumber(model.getHornNumber());
        if (model.getFarmerID() != null) {
            reindeer.setFarmer(farmerService.findById(model.getFarmerID()));
        } else {
            reindeer.setFarmer(null);
        }
        service.update(reindeer);
        return Response.ok(createModelByEntity(reindeer)).build();
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("id") Long id) {
        Reindeer reindeer = service.findById(id);
        if (reindeer == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        service.remove(reindeer);
        return Response.ok().build();
    }

    //automatizálni a mappelést, mapstruct
    public ReindeerModel createModelByEntity(Reindeer entity) {
        ReindeerModel model = new ReindeerModel();
        model.setId(entity.getId());
        model.setName(entity.getName());
        model.setLegsNumber(entity.getLegsNumber());
        model.setHornNumber(entity.getHornNumber());
        if (entity.getFarmer() != null) {
            model.setFarmerID(entity.getFarmer().getId());
        }
        return model;
    }
}
