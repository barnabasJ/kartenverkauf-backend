package at.fhv.teama.easyticket.server.venue.ticket;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static at.fhv.teama.easyticket.server.venue.ticket.TicketState.*;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TicketServiceTest {
    @Autowired private TicketService ticketService;


    @Before
    public void setup() {
        
    }

    @Test
    public void sellTicket() {
        Ticket t = new Ticket();
        t.setState(SOLD);
        assertTrue(ticketService.sellTicket(t));
        t.setState(FREE);
        assertTrue(ticketService.sellTicket(t));
        t.setState(RESERVERD);
        assertTrue(ticketService.sellTicket(t));
    }
}
