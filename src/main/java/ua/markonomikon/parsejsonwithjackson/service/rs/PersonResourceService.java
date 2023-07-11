package ua.markonomikon.parsejsonwithjackson.service.rs;

import io.quarkus.logging.Log;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import ua.markonomikon.parsejsonwithjackson.model.jsonproperty.person.Person;

import java.util.List;

import static ua.markonomikon.parsejsonwithjackson.management.AppConstants.PERSON_PATH;

/**
 * SERVICE: for our Person entity.
 */

@Path(PERSON_PATH)
public class PersonResourceService {

    /*
     * =======================================
     * ================ GET ==================
     * =======================================
     */

    /**
     * GET of all Person objects
     */

    @GET
    @Path("/all")
    public List<Person> getAllPerson() {
        return Person.listAll();
    }

    /*
     * =======================================
     * ================ POST =================
     * =======================================
     */

    /**
     * JSON data received directly as a Person object and being assigned to String (name) and to Integer (age)
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response createPerson(Person person) {

        String name = person.name;
        int age = person.age;

        person.persist();

        Log.info("Persisted.");

        return Response.ok("Person created: " + name + ", " + age).build();
    }
}
