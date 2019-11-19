package at.fhv.teama.easyticket.server.venue.ticket;

import at.fhv.teama.easyticket.dto.TicketDto;

import at.fhv.teama.easyticket.server.mapping.MapperContext;
import at.fhv.teama.easyticket.server.person.Person;
import at.fhv.teama.easyticket.server.venue.VenueController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class TicketControllerTest {
    @Autowired
    private TicketRepository ticketRepo;
    @Autowired
    private TicketController ticketController;
    @Autowired
    private VenueController venueController;
    private TicketMapper ticketMapper;

    @Test
    public void testBuyTickets() {

    }
}
