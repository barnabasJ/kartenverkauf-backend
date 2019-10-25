package at.fhv.teama.easyticket.server.venue;

import at.fhv.teama.easyticket.server.address.Address;
import at.fhv.teama.easyticket.server.program.Program;
import at.fhv.teama.easyticket.server.venue.ticket.Ticket;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
public class Venue {
    @Id
    @GeneratedValue
    private Long id;
    private LocalDateTime date;
    @ManyToOne
    private Address address;
    @ManyToOne
    private Program program;
    @OneToMany(fetch = FetchType.EAGER)
    private Set<Ticket> tickets = new HashSet<Ticket>();
}
