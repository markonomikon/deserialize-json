package ua.markonomikon.parsejsonwithjackson.service.rs;

import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import ua.markonomikon.parsejsonwithjackson.model.jsonbproperty.PersonJsonb;

import java.util.List;

import static ua.markonomikon.parsejsonwithjackson.management.AppConstants.PERSON_JSONB_PATH;

/**
 * SERVICE: for our Person (Jsonb) entity.
 */

@Path(PERSON_JSONB_PATH)
public class PersonJsonbServiceResource {
    /*
     * =======================================
     * ================ GET ==================
     * =======================================
     */

    /**
     * GET of all Person (Jsonb) objects
     */

    @GET
    @Path("/all")
    public List<PersonJsonb> getAllPerson() {
        return PersonJsonb.listAll();
    }

    /*
     * =======================================
     * ================ POST =================
     * =======================================
     */

    /**
     * JSON data received directly as a Person (Jsonb) object and being assigned to String (name) and to Integer (age) using @JsonbProperty
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response createPerson(PersonJsonb personJsonb) {

        String name = personJsonb.name;
        int age = personJsonb.age;

        personJsonb.persist();

        return Response.ok("Person (Jsonb) created: " + name + ", " + age).build();
    }
}
