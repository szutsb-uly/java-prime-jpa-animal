package hu.ulyssys.java.course.maven.rest;

import hu.ulyssys.java.course.maven.entity.Reindeer;
import hu.ulyssys.java.course.maven.rest.model.ReindeerModel;

import javax.ws.rs.Path;

@Path("/reindeer")
public class ReindeerRestService extends CoreRestService<Reindeer, ReindeerModel> {

    @Override
    protected Reindeer initNewEntity() {
        return new Reindeer();
    }

    @Override
    protected ReindeerModel initNewModel() {
        return new ReindeerModel();
    }

    @Override
    protected void populateEntityFromModel(Reindeer entity, ReindeerModel model) {
        super.populateEntityFromModel(entity, model);
        entity.setHornNumber(model.getHornNumber());
    }

    @Override
    protected ReindeerModel createModelFromEntity(Reindeer entity) {
        ReindeerModel reindeerModel = super.createModelFromEntity(entity);
        reindeerModel.setHornNumber(entity.getHornNumber());
        return reindeerModel;
    }
}
