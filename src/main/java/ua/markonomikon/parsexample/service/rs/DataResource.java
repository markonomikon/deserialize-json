package ua.markonomikon.parsexample.service.rs;

import io.quarkus.logging.Log;
import io.vertx.core.json.JsonObject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;

import ua.markonomikon.api.service.RepositoryService;
import ua.markonomikon.parsexample.model.Data;

import java.io.IOException;
import java.time.LocalDateTime;

@Path("/data")
public class DataResource extends RepositoryService<Data, String> {

    /*
     * =======================================
     * ================ GET ==================
     * =======================================
     */

    @GET
    @Path("/all")
    public void getAll(){
    }

    /*
     * =======================================
     * ================ POST =================
     * =======================================
     */

    @POST
    @Path("/entry")
    @Transactional
    public void dataEntry(JsonObject jsonObject) throws IOException {
        Log.info("persist: " + jsonObject.toString());

        Data data = new Data();
        data.data_to_parse = jsonObject.toString();
        data.received_date = LocalDateTime.now();
        data.persist();
    }

}
