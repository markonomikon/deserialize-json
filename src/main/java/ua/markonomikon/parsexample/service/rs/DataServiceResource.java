package ua.markonomikon.parsexample.service.rs;

import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.logging.Log;
import io.quarkus.panache.common.Sort;
import io.vertx.core.json.JsonObject;
import jakarta.inject.Singleton;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.Consumes;

import jakarta.ws.rs.core.MediaType;
import ua.markonomikon.api.service.RsRepositoryServiceV3;
import ua.markonomikon.parsexample.model.Data;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import static ua.markonomikon.parsexample.management.AppConstants.DATA_PATH;

@Path(DATA_PATH)
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Singleton
public class DataServiceResource extends RsRepositoryServiceV3<Data, String> {

    /*
     * =======================================
     * =========== DEFAULT ORDER =============
     * =======================================
     */

    @Override
    protected String getDefaultOrderBy() {
        return null;
    }

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
    @Transactional
    public void dataEntry(JsonObject jsonObject) throws IOException {
        Log.info("persist: " + jsonObject.toString());

        Data data = new Data();
        data.data_to_parse = jsonObject.toString();
        data.received_date = LocalDateTime.now();
        data.persist();

        Log.info("persisted: " + data);
    }

    /*
     * =======================================
     * ============= GET SEARCH ==============
     * =======================================
     */

    @Override
    public PanacheQuery<Data> getSearch(String orderBy) throws Exception {
        PanacheQuery<Data> search;
        Sort sort = sort(orderBy);
        if (sort != null) {
            search = Data.find(null, sort);
        } else {
            search = Data.find(null);
        }
        return search;
    }

}
