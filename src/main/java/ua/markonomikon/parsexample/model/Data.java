package ua.markonomikon.parsexample.model;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;


/**
 *
 */
@Entity
@Table(name = "data")
public class Data extends PanacheEntityBase {
    @Id
    @GeneratedValue
    public String uuid;
    public String data_to_parse;
    public LocalDateTime received_date;
}
