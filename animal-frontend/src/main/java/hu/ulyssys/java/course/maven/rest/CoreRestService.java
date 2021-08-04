package hu.ulyssys.java.course.maven.rest;

import hu.ulyssys.java.course.maven.entity.AbstractAnimal;
import hu.ulyssys.java.course.maven.rest.model.CoreRestModel;
import hu.ulyssys.java.course.maven.service.CoreService;
import hu.ulyssys.java.course.maven.service.FarmerService;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.util.stream.Collectors;

public abstract class CoreRestService<T extends AbstractAnimal, M extends CoreRestModel> {

    @Inject
    private FarmerService farmerService;
    @Inject
    private CoreService<T> catService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() {
        return Response.ok(catService.getAll().stream().map(this::createModelFromEntity).collect(Collectors.toList())).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response save(@Valid M model) {

        T entity = initNewEntity();
        populateEntityFromModel(entity, model);

        catService.add(entity);
        return Response.ok(createModelFromEntity(entity)).build();
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(@Valid M model) {
        T entity = catService.findById(model.getId());
        if (entity == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        populateEntityFromModel(entity, model);
        catService.update(entity);
        return Response.ok(createModelFromEntity(entity)).build();

    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("id") Long id) {
        T entity = catService.findById(id);
        if (entity == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        catService.remove(entity);
        return Response.ok().build();
    }

    protected void populateEntityFromModel(T entity, M model) {
        if (model.getFarmerID() != null) {
            entity.setFarmer(farmerService.findById(model.getFarmerID()));
        }
        entity.setName(model.getName());
        entity.setLegsNumber(model.getLegsNumber());
    }

    protected M createModelFromEntity(T entity) {
        M model = initNewModel();
        model.setLegsNumber(entity.getLegsNumber());
        model.setId(entity.getId());
        model.setName(entity.getName());
        if (entity.getFarmer() != null) {
            model.setFarmerID(entity.getFarmer().getId());
        }
        return model;
    }

    //Generikus típus megszerzés, és reflection alapú objektum inicializálása
    protected T initNewEntity() {

        try {
            // A konténer, beinjectáláskor, egy Proxy obejktumot hoz létre, ezért kérszer kell leolvasnunk ebben az esetben a ősosztály, és annak típusát
            // Ha model paraméterre szükség, akkor 1 indexű elem kellene az array-ből
            Class<T> type = (Class<T>) (((ParameterizedType)((Class)getClass().getGenericSuperclass()).getGenericSuperclass())).getActualTypeArguments()[1];
            return type.getConstructor().newInstance();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    protected abstract M initNewModel();

}
