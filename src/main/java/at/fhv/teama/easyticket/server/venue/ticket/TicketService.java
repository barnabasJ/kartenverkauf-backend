package at.fhv.teama.easyticket.server.venue.ticket;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class TicketService {
  private final TicketRepository ticketRepository;

  public boolean sellTicket(Ticket ticket) {
    ticket.setState(TicketState.SOLD);
    Ticket soldTicket = ticketRepository.save(ticket);
    return soldTicket.getState() == TicketState.SOLD;
  }

  public boolean isTicketReservedByPerson(Ticket t) {
    return ticketRepository.findAllByPersonAndState(t.getPerson(), TicketState.RESERVERD).size()
        > 0;
  }

  public boolean reserveTicket(Ticket ticket) {
    ticket.setState(TicketState.RESERVERD);
    Ticket soldTicket = ticketRepository.save(ticket);
    return soldTicket.getState() == TicketState.RESERVERD;
  }

  public boolean unreserveTicket(Ticket ticket) {
    ticket.setState(TicketState.FREE);
    Ticket soldTicket = ticketRepository.save(ticket);
    return soldTicket.getState() == TicketState.FREE;
  }

  public Set<Ticket> getTicketsForVenue(long id) {
    return ticketRepository.findAllByVenueId(id);
  }

  public Set<Ticket> getTicketsByIds(Long[] ticketIds) {
    return StreamSupport.stream(
            ticketRepository.findAllById(Arrays.asList(ticketIds)).spliterator(), false)
        .collect(Collectors.toSet());
  }
}
