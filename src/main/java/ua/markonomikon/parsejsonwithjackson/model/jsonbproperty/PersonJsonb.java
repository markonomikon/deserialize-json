package ua.markonomikon.parsejsonwithjackson.model.jsonbproperty;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.json.bind.annotation.JsonbProperty;
import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

/**
 * ENTITY: containing received data that has been parsed and processed.
 * - String 'name': "name" field of received data.
 * - int 'age': "age" field of received data.
 */

@Entity
@Table(name = "person_jsonb")
public class PersonJsonb extends PanacheEntityBase {
        @GeneratedValue(generator = "uuid")
        @GenericGenerator(name = "uuid", strategy = "uuid2")
        @Column(name = "uuid", unique = true)
        @Id
        public String uuid;
        @JsonbProperty("name")
        public String name;
        @JsonbProperty("age")
        public int age;
}
