package ua.markonomikon.api.service;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import io.quarkus.logging.Log;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.core.Response;

import java.io.Serial;
import java.io.Serializable;
import java.util.logging.Logger;

public abstract class RepositoryService<T extends PanacheEntityBase, U> extends ResponseService implements
        Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @Inject
    EntityManager entityManager;

    protected EntityManager getEntityManager() {
        return entityManager;
    }

    @POST
    @Transactional
    public Response persist(T object) {
        try {
            entityManager.persist(object);
            if (object == null) {
                Log.error("Failed to create resource: " + object);
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(object).build();
            } else {
                return Response.status(Response.Status.OK).entity(object).build();
            }
        } catch (Exception e) {
            Log.errorv(e, "persist");
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(object).build();
        }
    }

}
