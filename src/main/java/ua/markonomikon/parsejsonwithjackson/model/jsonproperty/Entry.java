package ua.markonomikon.parsejsonwithjackson.model.jsonproperty;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * RECORD: that allows for serialization/deserialization to JSON format.
 * @param id
 * @param metadata
 */

public record Entry(
        @JsonProperty("id") String id,
        @JsonProperty("metadata") List<Metadata> metadata
) {}









/*
 * Created by markonomikon.
 */
