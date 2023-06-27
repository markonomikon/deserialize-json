package ua.markonomikon.parsexample.model;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

/**
 * ENTITY: containing received data that has been parsed and processed.
 * - String 'object': "object" field of received data.
 * - String 'id': "id" field of received data.
 * - String 'sender_phone_number': "sender_phone_number" field of received data.
 * - String 'sender_name': "sender_name" field of received data.
 * - String 'sender_surname': "sender_surname" field of received data.
 * - String 'recipient_phone_number': "recipient_phone_number" field of received data.
 * - String 'recipient_name': "recipient_name" field of received data.
 * - String 'recipient_surname': "recipient_surname" field of received data.
 * check the data.md file in docs folder.
 */

@Entity
@Table(name = "parsed_data")
public class ParsedData extends PanacheEntityBase {
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "uuid", unique = true)
    @Id
    public String uuid;
    public String object;
    public String id;
    public String sender_phone_number;
    public String sender_name;
    public String sender_surname;
    public String recipient_phone_number;
    public String recipient_name;
    public String recipient_surname;

}
