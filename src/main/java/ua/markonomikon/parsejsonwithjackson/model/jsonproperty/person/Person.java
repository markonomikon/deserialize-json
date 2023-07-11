package ua.markonomikon.parsejsonwithjackson.model.jsonproperty.person;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

/**
 * ENTITY: containing received data that has been parsed and processed.
 * - String 'name': "name" field of received data.
 * - int 'age': "age" field of received data.
 */

@Entity
@Table(name = "person")
public class Person extends PanacheEntityBase {
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "uuid", unique = true)
    @Id
    public String uuid;
    @JsonProperty("name")
    public String name;
    @JsonProperty("age")
    public int age;
}