package at.fhv.teama.easyticket.server.venue.ticket;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class TicketServiceTest {
    @Autowired private TicketRepo venueRepo;
    @Autowired private TicketService venueService;

    private Ticket ticket;

    @Before
    public void setup() {
        ticket = new Ticket();
        ticket.setCategory(new Category());
    }

    @Test
    public void getAllBooked() {

    }
}
