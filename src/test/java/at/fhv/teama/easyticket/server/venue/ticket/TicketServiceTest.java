package at.fhv.teama.easyticket.server.venue.ticket;

import at.fhv.teama.easyticket.server.venue.VenueService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;
import java.util.Set;

import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TicketServiceTest {
    @Autowired private VenueService venueService;
    @Autowired private TicketService ticketService;
    @Autowired private TicketRepository ticketRepo;

    private Ticket ticket;

    @Before
    public void setup() {
        ticket = new Ticket();
        ticket.setState(TicketState.FREE);
        ticketRepo.save(ticket);
    }

    @Test
    public void getFreeTickets() {
        Set<Ticket> free_tickets = ticketRepo.getAllByState(TicketState.RESERVERD);
    }

    @Test
    public void sellTicket() {
        boolean sold = ticketService.sellTicket(ticket);
        assertTrue(sold);
    }
}
