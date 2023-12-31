# Parse JSON using Jackson
is a simple example of how to deserialize JSON using [Jackson](https://mvnrepository.com/artifact/com.fasterxml.jackson.core/jackson-core). The project is in 
java using [Quarkus](https://quarkus.io/) framework. You can find 2 ways of deserializing:

- using [JsonNode](#jsonnode)
- using [JsonProperty annotation](#jsonproperty)
- [JsonNode](#json-nodes) usage in this application
- [JsonProperty](#json-property) usage in this application

### JsonNode
JsonNode refers to a data structure used to represent and manipulate JSON 
(JavaScript Object Notation) data. Key points:
- **Representation of JSON Data**: JsonNode represents a node in a JSON structure. 
It can be a JSON object, JSON array, JSON primitive value (such as a string, number, boolean), or a null value.
- **Tree Structure**: JsonNode forms a hierarchical tree-like structure that mirrors the structure of the JSON data.
Each node can have child nodes and can be accessed and manipulated accordingly.
- **Navigation and Access**: JsonNode provides methods to navigate the JSON structure and access its values. 
For example, you can retrieve the value of a specific field within a JSON object or iterate over the elements of a JSON array.
- **Conversion**: JsonNode allows conversion between JSON and other data formats. You can convert a JsonNode to a JSON string or convert a JSON string to a JsonNode representation.
- **Immutable and Read-Only**: In many implementations, JsonNode objects are immutable, meaning their state cannot be modified once created. They are designed for read-only access to the JSON data.
To modify JSON data, you typically create a new JsonNode with the desired changes.
Steps using JsonNode:
- **Parsing JSON**: You parse a JSON string using a JSON processing library, such as Jackson, which will create a JsonNode object representing the JSON structure.
- **Accessing Data**: Once you have the JsonNode object, you can traverse the JSON structure by accessing child nodes, retrieving field values, or iterating over array elements.
- **Manipulating Data**: If you need to modify the JSON data, you create a new JsonNode object with the desired changes. This can involve adding or removing fields, updating values, or restructuring the JSON tree.
- **Serializing JSON**: Finally, when you want to convert the modified JsonNode back to a JSON string, you use the library's serialization functionality to generate the updated JSON representation.
#### example use of JsonNode:
let's suppose we have this JSON:
```
{
    "name":"Timon",
    "surname":"Pumba",
    "dateofbirth":
        {
        "day":"30",
        "month":"12",
        "year":"1995"
        }
}
```
we'll use the JsonNode like follows:
```
String jsonString = "{\"name\":\"Timon\",\"surname\":\"Pumba\",\"dateofbirth\":{\"day\":\"30\","month\":\"12\",\"year\":\"1995\"}}";

ObjectMapper objectMapper = new ObjectMapper();
JsonNode rootNode = objectMapper.readTree(jsonString);

//name & surname
String name = rootNode.get("name").asText();
String surname = rootNode.get("surname").asText();

//date of birth
String day = rootNode.get("dateofbirth").get(0).get("day").asText();
String month = rootNode.get("dateofbirth").get(0).get("month").asText();
String year = rootNode.get("dateofbirth").get(0).get("year").asText();
```
or we can directly populate an object like follows:
```
//our object
public class Person {
    public String name;
    public String surname;
    public LocalDate dateofbirth;
}
```
```
@Transactional
public static void newPerson(String jsonString){
    //create a new personJsonb instance
    Person personJsonb = new Person();
    
    personJsonb.name = rootNode.get("name").asText();
    personJsonb.surname = rootNode.get("surname").asText();
    
    //date of birth
    String day = rootNode.get("dateofbirth").get(0).get("day").asText();
    String month = rootNode.get("dateofbirth").get(0).get("month").asText();
    String year = rootNode.get("dateofbirth").get(0).get("year").asText();
    
    //convert the strings into LocalDate object
    LocalDate date = LocalDate.of(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day));
    personJsonb.dateofbirth = date;
    
    //persist the new personJsonb
    personJsonb.persist();
}
```

### JsonProperty
JsonProperty is an annotation used to define the mapping between JSON property names and Java object fields or methods. Key points:

- **Mapping JSON Properties**: JSON data consists of key-value pairs, where the keys are property names. When deserializing JSON into Java objects or serializing Java objects into JSON, the JsonProperty annotation allows you to explicitly specify the mapping between JSON property names and Java object fields or methods.
- **Deserialization**: When deserializing JSON into Java objects, you can use JsonProperty to specify the name of the JSON property that should be mapped to a particular field or method in the Java object. This is useful when the JSON property names do not match the Java object's field or method names.
- **Serialization**: Similarly, when serializing Java objects into JSON, you can use JsonProperty to define the name of the JSON property that should be used to represent a specific field or method in the Java object. This allows you to customize the JSON property names according to your requirements.
- **Usage**: JsonProperty is typically used as a decorator or annotation in programming languages that support annotations, such as Java. By applying the JsonProperty annotation to a field or method, you can specify the desired JSON property name.

#### example use of JsonProperty annotation:
let's suppose we have this JSON:

```
{
    "name":"Timon",
    "surname":"Pumba",
    "dateofbirth":
        {
        "day":"30",
        "month":"12",
        "year":"1995"
        }
}
```
we'll use the JsonProperty annotation like follows:
```
//our object with annotated field's
public class Person {

        @JsonProperty("name")
        public String name;
        
        @JsonProperty("surname") 
        public String surname;
        
        @JsonProperty("dateofbirth") 
        public Birthday birthday;
}
```
```
//our object contained inside Person object
public record Birthday(
        @JsonProperty("day") 
        String day,
        
        @JsonProperty("month") 
        String month,
        
        @JsonProperty("year") 
        String year
) {}
```
now we map the JSON data to Person object and persist it:
```
        String jsonString = "{\"name\":\"Timon\",\"surname\":\"Pumba\",\"dateofbirth\":{\"day\":\"30\","month\":\"12\",\"year\":\"1995\"}}";
        
        ObjectMapper objectMapper = new ObjectMapper();        
        Person personJsonb = objectMapper.readValue(jsonString, Person.class);   
           
        personJsonb.persist();
```

### Requests
##### JSON property
to test the functionality of @JsonProperty annotation's make a POST request to:
```
http://localhost:8080/api/json_data/entry
```
with the following json body example:
```
{
    "object":"message",
    "entry":
        [
            {
            "id":"100253349659316",
            "metadata":
                [
                    {
                    "from":
                        {
                        "number":"3895412487",
                        "name":"Jick",
                        "surname":"Mack"
                        },
                    "to":
                        {
                        "number":"0898234769",
                        "name":"Mick",
                        "surname":"Jack"
                        }
                    }
                ]         
            }
        ]
}
```
what will happen is that the json will be mapped automatically into the 'DataEvent' pojo and so on:
```
DataEvent dataEvent = objectMapper.readValue(jsonObject, DataEvent.class);
```
after we'll create and populate our 'ParsedData' entity using the pojo (dataEvent) in which we mapped our json.

##### JSON nodes
to test the functionality of json nodes make a POST request to:
```
http://localhost:8080/api/data/entry_no_scheduler
```
with the following json body example:
```
{
    "object":"message",
    "entry":
        [
            {
            "id":"100253349659316",
            "metadata":
                [
                    {
                    "from":
                        {
                        "number":"3895412487",
                        "name":"Jick",
                        "surname":"Mack"
                        },
                    "to":
                        {
                        "number":"0898234769",
                        "name":"Mick",
                        "surname":"Jack"
                        }
                    }
                ]         
            }
        ]
}
```
what will happen is that we'll pass our json with an object mapper to a method which will process it using json nodes:
```
JsonNode rootNode = objectMapper.readTree(jsonString);

String object = rootNode.get("object").asText();
JsonNode entryNode = rootNode.get("entry");

etc...
```
after we'll create and populate our 'ParsedData' entity using nodes.

##### Timer
to test the functionality of a timer that processes data taken from an existing tables column, make a POST request to:
```
http://localhost:8080/api/data/entry
```
with the following json body example:
```
{
    "object":"message",
    "entry":
        [
            {
            "id":"100253349659316",
            "metadata":
                [
                    {
                    "from":
                        {
                        "number":"3895412487",
                        "name":"Jick",
                        "surname":"Mack"
                        },
                    "to":
                        {
                        "number":"0898234769",
                        "name":"Mick",
                        "surname":"Jack"
                        }
                    }
                ]         
            }
        ]
}
```
what will happen is that we'll create and populate an entities field with our json as string. In our case we'll populate the 'data_to_parse' field of an object 
simply called 'Data'. Once populated, with a timer we'll process the string containing our json and create & populate our 'ParsedData' entity. In the timer 
we can use both ways of parsing we've seen before. We can parse our json using 'DataEvent' so using json property annotations, or we can parse it using json nodes, it's up to you.



