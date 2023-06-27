package ua.markonomikon.parsexample.service.timer;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.quarkus.logging.Log;
import io.quarkus.scheduler.Scheduled;
import jakarta.inject.Singleton;
import jakarta.inject.Inject;
import ua.markonomikon.parsexample.model.Data;
import ua.markonomikon.parsexample.service.util.DataUtil;

import java.util.List;

import static org.wildfly.common.Assert.assertTrue;

@Singleton
public class DataParser {
    @Inject
    ObjectMapper objectMapper;

    /*
     * =======================================
     * =============== TIMER =================
     * =======================================
     */

    @Scheduled(every = "5s", concurrentExecution = Scheduled.ConcurrentExecution.SKIP)
    public void parseData(){
        Log.info("ParseData: RUNNING");

        try {
            List<Data> list = Data.listAll();
            for (Data data : list) {
                try {

                    if (!data.parsed){
                        assertTrue(data.data_to_parse != null);
                        DataUtil.parseUsingNodesWithScheduler(data, objectMapper);
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
