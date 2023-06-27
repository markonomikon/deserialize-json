# parse-xample
is a simple example of how to deserialize JSON using Jackson. The project is in 
java using Quarkus framework. You can find 2 ways of deserializing:

- using JsonNode
- using JsonProperty annotation

### JsonNode
JsonNode refers to a data structure used to represent and manipulate JSON 
(JavaScript Object Notation) data. Key points:
- *Representation of JSON Data*: JsonNode represents a node in a JSON structure. 
It can be a JSON object, JSON array, JSON primitive value (such as a string, number, boolean), or a null value.
- *Tree Structure*: JsonNode forms a hierarchical tree-like structure that mirrors the structure of the JSON data.
Each node can have child nodes and can be accessed and manipulated accordingly.
- *Navigation and Access*: JsonNode provides methods to navigate the JSON structure and access its values. 
For example, you can retrieve the value of a specific field within a JSON object or iterate over the elements of a JSON array.
- *Conversion*: JsonNode allows conversion between JSON and other data formats. You can convert a JsonNode to a JSON string or convert a JSON string to a JsonNode representation.
- *Immutable and Read-Only*: In many implementations, JsonNode objects are immutable, meaning their state cannot be modified once created. They are designed for read-only access to the JSON data.
To modify JSON data, you typically create a new JsonNode with the desired changes.
Steps using JsonNode:
- Parsing JSON: You parse a JSON string using a JSON processing library, such as Jackson, which will create a JsonNode object representing the JSON structure.
- Accessing Data: Once you have the JsonNode object, you can traverse the JSON structure by accessing child nodes, retrieving field values, or iterating over array elements.
- Manipulating Data: If you need to modify the JSON data, you create a new JsonNode object with the desired changes. This can involve adding or removing fields, updating values, or restructuring the JSON tree.
- Serializing JSON: Finally, when you want to convert the modified JsonNode back to a JSON string, you use the library's serialization functionality to generate the updated JSON representation.
### JsonProperty
JsonProperty is an annotation used to define the mapping between JSON property names and Java object fields or methods. Key points:

- Mapping JSON Properties: JSON data consists of key-value pairs, where the keys are property names. When deserializing JSON into Java objects or serializing Java objects into JSON, the JsonProperty annotation allows you to explicitly specify the mapping between JSON property names and Java object fields or methods.
- Deserialization: When deserializing JSON into Java objects, you can use JsonProperty to specify the name of the JSON property that should be mapped to a particular field or method in the Java object. This is useful when the JSON property names do not match the Java object's field or method names.
- Serialization: Similarly, when serializing Java objects into JSON, you can use JsonProperty to define the name of the JSON property that should be used to represent a specific field or method in the Java object. This allows you to customize the JSON property names according to your requirements.
- Usage: JsonProperty is typically used as a decorator or annotation in programming languages that support annotations, such as Java. By applying the JsonProperty annotation to a field or method, you can specify the desired JSON property name.

#### example use of JsonProperty annotation

