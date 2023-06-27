package ua.markonomikon.parsexample.service.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.quarkus.logging.Log;
import io.vertx.core.json.JsonObject;
import jakarta.transaction.Transactional;
import ua.markonomikon.parsexample.model.Data;
import ua.markonomikon.parsexample.model.ParsedData;

public class DataUtil {

    @Transactional
    public static void parseUsingNodesWithScheduler(Data data, ObjectMapper objectMapper){
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
            String from_name = fromNode.get("name").asText();;
            String from_surname = fromNode.get("surname").asText();;

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

    @Transactional
    public static void parseUsingNodesNoScheduler(JsonObject jsonObject, ObjectMapper objectMapper){
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
            String from_name = fromNode.get("name").asText();;
            String from_surname = fromNode.get("surname").asText();;

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
}
