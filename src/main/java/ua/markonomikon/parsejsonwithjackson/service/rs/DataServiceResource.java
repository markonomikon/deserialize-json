package ua.markonomikon.parsejsonwithjackson.service.rs;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.quarkus.logging.Log;
import io.vertx.core.json.JsonObject;
import jakarta.inject.Singleton;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.MediaType;
import ua.markonomikon.parsejsonwithjackson.model.jsonodes.Data;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import static ua.markonomikon.parsejsonwithjackson.management.AppConstants.DATA_PATH;
import static ua.markonomikon.parsejsonwithjackson.service.util.DataUtil.parseDirectly;

/**
 * SERVICE: entry of data which will be parsed using JsonNode
 */

@Path(DATA_PATH)
@Singleton
public class DataServiceResource{

    /*
     * =======================================
     * ================ GET ==================
     * =======================================
     */

    @GET
    @Path("/all")
    public List<Data> getAllData() {
        return Data.listAll();
    }

    /*
     * =======================================
     * ================ POST =================
     * =======================================
     */

    @POST
    @Path("/entry")
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public void dataEntry(JsonObject jsonObject) throws IOException {
        Log.info("Persisting: " + jsonObject.toString());

        Data data = new Data();
        data.data_to_parse = jsonObject.toString();
        data.received_date = LocalDateTime.now();
        data.parsed = false;
        data.persist();

        Log.info("Persisted.");
    }

    @POST
    @Path("/entry_no_scheduler")
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public void dataEntryDirectParse(JsonObject jsonObject) throws IOException {
        Log.info("Persisting: " + jsonObject.toString());

        ObjectMapper objectMapper = new ObjectMapper();
        parseDirectly(jsonObject, objectMapper);

        Log.info("Persisted.");
    }
}



/*
 * Created by markonomikon.
 */
