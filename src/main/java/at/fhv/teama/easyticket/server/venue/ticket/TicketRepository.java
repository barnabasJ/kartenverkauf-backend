package at.fhv.teama.easyticket.server.venue.ticket;

import at.fhv.teama.easyticket.server.person.Person;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

public interface TicketRepository extends CrudRepository<Ticket, Long> {
    Set<Ticket> findById(long id);
    Set<Ticket> findAllByPersonAndState(Person person, TicketState state);
    Set<Ticket> findAllByVenueId(Long id);
}

