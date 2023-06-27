package ua.markonomikon.parsexample.service.rs;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.inject.Singleton;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.MediaType;
import ua.markonomikon.parsexample.model.jsonproperty.DataEvent;

import static ua.markonomikon.parsexample.management.AppConstants.JSON_DATA_PATH;
import static ua.markonomikon.parsexample.service.util.DataUtil.parseDataFromEvent;

/**
 * SERVICE: entry of data which will be mapped into DataEvent record
 */

@Path(JSON_DATA_PATH)
@Singleton
public class DataEventServiceResource {

    /*
     * =======================================
     * ================ POST =================
     * =======================================
     */

    @POST
    @Path("/entry")
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public void dataEntry(String jsonObject) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        DataEvent dataEvent = objectMapper.readValue(jsonObject, DataEvent.class);
        parseDataFromEvent(dataEvent);
    }
}
