package at.fhv.teama.easyticket.server.venue.ticket;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TicketService {
    private final TicketRepository ticketRepository;

    public boolean sellTicket(Ticket ticket) {
        ticket.setState(TicketState.SOLD);
        Ticket soldTicket = ticketRepository.save(ticket);
        return soldTicket.getState() == TicketState.SOLD;
    }

    public boolean reserveTicket(Ticket ticket) {
        if (ticket.getPerson() == null) return false;
        ticket.setState(TicketState.RESERVERD);
        Ticket soldTicket = ticketRepository.save(ticket);
        return soldTicket.getState() == TicketState.RESERVERD;
    }

}
