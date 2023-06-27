package ua.markonomikon.parsejsonwithjackson.service.timer;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.quarkus.logging.Log;
import io.quarkus.scheduler.Scheduled;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import ua.markonomikon.parsejsonwithjackson.model.jsonodes.Data;
import ua.markonomikon.parsejsonwithjackson.service.util.DataUtil;

import java.util.List;

import static org.wildfly.common.Assert.assertTrue;

/**
 * TIMER: that lists all 'Data' objects every 30 seconds and parse the 'data_to_parse' field content using JsonNode.
 * Once the data is parsed, it is used to populate an instance of 'ParsedData' which is persisted.
 */

@Singleton
public class ParseDataTimer {
    @Inject
    ObjectMapper objectMapper;

    /*
     * =======================================
     * =============== TIMER =================
     * =======================================
     */

    @Scheduled(every = "30s", concurrentExecution = Scheduled.ConcurrentExecution.SKIP)
    public void scheduledDataParse(){
        Log.info("ParseDataTimer: RUNNING");

        try {
            List<Data> list = Data.listAll();
            for (Data data : list) {
                try {

                    if (!data.parsed){
                        assertTrue(data.data_to_parse != null);
                        DataUtil.parseForScheduler(data, objectMapper);
                    }

                } catch (Exception e) {
                    Log.error("Error: processing 'Data': " + e.getMessage());

                }
            }
        } catch (Exception e) {
            Log.error("Error: retrieving 'Data': " + e.getMessage());

        }
    }
}


/*
 * Created by markonomikon.
 */
