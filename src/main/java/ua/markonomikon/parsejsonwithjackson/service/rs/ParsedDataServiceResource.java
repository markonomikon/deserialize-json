package ua.markonomikon.parsejsonwithjackson.service.rs;

import jakarta.inject.Singleton;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import ua.markonomikon.parsejsonwithjackson.model.ParsedData;

import java.util.List;

import static ua.markonomikon.parsejsonwithjackson.management.AppConstants.PARSED_DATA_PATH;

/**
 * SERVICE: just to get our parsed data.
 */

@Path(PARSED_DATA_PATH)
@Singleton
public class ParsedDataServiceResource {

    /*
     * =======================================
     * ================ GET ==================
     * =======================================
     */

    @GET
    @Path("/all")
    public List<ParsedData> getAllData() {
        return ParsedData.listAll();
    }

}



/*
 * Created by markonomikon.
 */
