package ua.markonomikon.parsejsonwithjackson.model.jsonproperty;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * RECORD: that allows for serialization/deserialization to JSON format. This is the main record for our received data.
 * @param entry
 * @param object
 */

public record DataEvent(
        @JsonProperty("entry") List<Entry> entry,
        @JsonProperty("object") String object
) {}











/*
 * Created by markonomikon.
 */
