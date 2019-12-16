package at.fhv.teama.easyticket.server.venue.ticket;

import at.fhv.teama.easyticket.server.person.Person;
import at.fhv.teama.easyticket.server.venue.Venue;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Ticket {
    @Id
    @GeneratedValue
    private Long id;
    private Integer x;
    private Integer y;
    private TicketState state;
    @ManyToOne
    private Category category;
    @ManyToOne
    @JsonIgnore
    private Venue venue;
    @ManyToOne
    private Person person;
}
