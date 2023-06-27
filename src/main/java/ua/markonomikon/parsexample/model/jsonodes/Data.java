package ua.markonomikon.parsexample.model.jsonodes;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;

/**
 * ENTITY: containing basic info about the received data.
 * - String 'data_to_parse': contains received data.
 * - LocalDateTime 'received_date': date when the data was received.
 * - boolean 'parsed': true/false data is parsed.
 */

@Entity
@Table(name = "data")
public class Data extends PanacheEntityBase {

    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "uuid", unique = true)
    @Id
    public String uuid;
    public String data_to_parse;
    public LocalDateTime received_date;
    public boolean parsed;

    @Override
    public String toString() {
        return "Data{" +
                "uuid='" + uuid + '\'' +
                ", data_to_parse='" + data_to_parse + '\'' +
                ", received_date=" + received_date +
                ", parsed=" + parsed +
                '}';
    }
}
