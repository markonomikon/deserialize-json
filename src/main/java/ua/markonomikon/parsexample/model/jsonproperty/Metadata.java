package ua.markonomikon.parsexample.model.jsonproperty;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * RECORD: that allows for serialization/deserialization to JSON format.
 * @param from
 * @param to
 */

public record Metadata(
        @JsonProperty("from") From from,
        @JsonProperty("to") To to
) {}
