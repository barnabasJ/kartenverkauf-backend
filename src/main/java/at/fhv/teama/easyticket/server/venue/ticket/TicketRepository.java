package at.fhv.teama.easyticket.server.venue.ticket;

import org.springframework.data.repository.CrudRepository;

import java.util.Set;

public interface TicketRepository extends CrudRepository<Ticket, Long> {
    Set<Ticket> getAllByCategory(Category category);

    Set<Ticket> getAllByState(TicketState state);
}
