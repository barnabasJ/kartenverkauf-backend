package at.fhv.teama.easyticket.server.venue.ticket;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TicketService {
  private final TicketRepo ticketRepo;

  public boolean sellTicket(Ticket ticket) {
    ticket.setState(TicketState.SOLD);
    Ticket soldTicket = ticketRepo.save(ticket);
    if (soldTicket.getState() == TicketState.SOLD) return true;
    return false;
  }

  public boolean reserveTicket(Ticket ticket) {
    if (ticket.getPerson() == null) return false;
      ticket.setState(TicketState.RESERVERD);
      Ticket soldTicket = ticketRepo.save(ticket);
      if (soldTicket.getState() == TicketState.RESERVERD) return true;
      return false;
  }
}
