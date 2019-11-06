package at.fhv.teama.easyticket.server.venue.ticket;

import at.fhv.teama.easyticket.dto.TicketDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import static at.fhv.teama.easyticket.server.venue.ticket.TicketState.FREE;

@Controller
@RequiredArgsConstructor
public class TicketController {
    private final TicketService ticketService;
    private final TicketRepository ticketRepo;

    @Transactional
    public Set<TicketDto> buyTickets(Collection<TicketDto> tickets) {
        Set<TicketDto> unavailable = new HashSet<>();
        Set<Ticket> available = new HashSet<>();

        for (TicketDto ticketDto : tickets) {
            Ticket ticket = ticketRepo.findById(ticketDto.getId()).get();
            if (ticket.getState() != FREE) unavailable.add(ticketDto);
            else available.add(ticket);
        }

        // Check availability and return unavailable tickets
        if (unavailable.size() > 0) return unavailable;
        else {
            for (Ticket ticket : available) {
                ticketService.sellTicket(ticket);
            }
        }

        // All tickets available - none to return
        return new HashSet<>();
    }

}

