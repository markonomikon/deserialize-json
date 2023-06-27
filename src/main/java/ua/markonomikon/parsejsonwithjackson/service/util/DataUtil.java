package ua.markonomikon.parsejsonwithjackson.service.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.quarkus.logging.Log;
import io.vertx.core.json.JsonObject;
import jakarta.transaction.Transactional;
import ua.markonomikon.parsejsonwithjackson.model.ParsedData;
import ua.markonomikon.parsejsonwithjackson.model.jsonodes.Data;
import ua.markonomikon.parsejsonwithjackson.model.jsonproperty.DataEvent;

/**
 * UTIL: class with 3 methods and 2 ways of deserialize JSON data received.
 */

public class DataUtil {

    /*
     * =======================================
     * ========= USING JSON NODE =============
     * =======================================
     */

    /**
     * Method where we instance, populate and persist 'ParsedData' entity and update 'Data' entity using the Data if 'parsed' equals false.
     * @param data
     * @param objectMapper
     */

    @Transactional
    public static void parseForScheduler(Data data, ObjectMapper objectMapper){
        String jsonString = data.data_to_parse;

        Log.info("Parsing JSON: " + jsonString);

        try {
            JsonNode rootNode = objectMapper.readTree(jsonString);

            String object = rootNode.get("object").asText();
            JsonNode entryNode = rootNode.get("entry");

            String id = entryNode.get(0).get("id").asText();

            JsonNode metadataNode = entryNode.get(0).get("metadata");

            JsonNode fromNode = metadataNode.get(0).get("from");
            String from_number = fromNode.get("number").asText();
            String from_name = fromNode.get("name").asText();
            String from_surname = fromNode.get("surname").asText();

            JsonNode toNode = metadataNode.get(0).get("to");
            String to_number = toNode.get("number").asText();
            String to_name = toNode.get("name").asText();
            String to_surname = toNode.get("surname").asText();

            // INSTANCE NEW OBJECT FOR PARSED DATA
            ParsedData parsedData = new ParsedData();
            parsedData.object = object;
            parsedData.id = id;
            parsedData.sender_phone_number = from_number;
            parsedData.sender_name = from_name;
            parsedData.sender_surname = from_surname;
            parsedData.recipient_phone_number = to_number;
            parsedData.recipient_name = to_name;
            parsedData.recipient_surname = to_surname;
            parsedData.persistAndFlush();

            // UPDATE DATA
            data.parsed = true;
            Data.getEntityManager().merge(data);

            Log.info("ParsedData: " + parsedData.toString());

        } catch (JsonProcessingException e) {
            Log.error("Error: parsing JSON: " + e.getMessage());
        }
    }

    /**
     * Method where we instance, populate and persist 'ParsedData' entity, using directly the data that we received.
     * @param jsonObject
     * @param objectMapper
     */

    @Transactional
    public static void parseDirectly(JsonObject jsonObject, ObjectMapper objectMapper){
        String jsonString = jsonObject.toString();

        Log.info("Parsing JSON: " + jsonString);

        try {
            JsonNode rootNode = objectMapper.readTree(jsonString);

            String object = rootNode.get("object").asText();
            JsonNode entryNode = rootNode.get("entry");

            String id = entryNode.get(0).get("id").asText();

            JsonNode metadataNode = entryNode.get(0).get("metadata");

            JsonNode fromNode = metadataNode.get(0).get("from");
            String from_number = fromNode.get("number").asText();
            String from_name = fromNode.get("name").asText();
            String from_surname = fromNode.get("surname").asText();

            JsonNode toNode = metadataNode.get(0).get("to");
            String to_number = toNode.get("number").asText();
            String to_name = toNode.get("name").asText();
            String to_surname = toNode.get("surname").asText();

            // INSTANCE NEW OBJECT FOR PARSED DATA
            ParsedData parsedData = new ParsedData();
            parsedData.object = object;
            parsedData.id = id;
            parsedData.sender_phone_number = from_number;
            parsedData.sender_name = from_name;
            parsedData.sender_surname = from_surname;
            parsedData.recipient_phone_number = to_number;
            parsedData.recipient_name = to_name;
            parsedData.recipient_surname = to_surname;
            parsedData.persistAndFlush();

            Log.info("ParsedData: " + parsedData);

        } catch (JsonProcessingException e) {
            Log.error("Error: parsing JSON: " + e.getMessage());
        }
    }

    /*
     * =======================================
     * ========= USING JSON PROPERTY =========
     * =======================================
     */

    /**
     * Method where we instance, populate and persist 'ParsedData' entity, using 'dataEvent' into which we mapped data that we received.
     * @param dataEvent
     */

    @Transactional
    public static void parseDataFromEvent(DataEvent dataEvent){
        // INSTANCE NEW OBJECT FOR PARSED DATA
        ParsedData parsedData = new ParsedData();
        parsedData.object = dataEvent.object();
        parsedData.id = dataEvent.entry().get(0).id();
        parsedData.sender_phone_number = dataEvent.entry().get(0).metadata().get(0).from().number();
        parsedData.sender_name = dataEvent.entry().get(0).metadata().get(0).from().name();
        parsedData.sender_surname = dataEvent.entry().get(0).metadata().get(0).from().surname();
        parsedData.recipient_phone_number = dataEvent.entry().get(0).metadata().get(0).to().number();
        parsedData.recipient_name = dataEvent.entry().get(0).metadata().get(0).to().name();
        parsedData.recipient_surname = dataEvent.entry().get(0).metadata().get(0).to().surname();
        parsedData.persistAndFlush();
    }

}






/*
 * Created by markonomikon.
 */
