package at.fhv.teama.easyticket.server.venue.ticket;

import org.springframework.data.repository.CrudRepository;

public interface TicketRepo extends CrudRepository<Ticket, Long> {

}
