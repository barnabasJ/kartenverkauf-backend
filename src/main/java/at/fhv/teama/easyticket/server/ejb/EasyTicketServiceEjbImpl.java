package at.fhv.teama.easyticket.server.ejb;

import at.fhv.teama.easyticket.dto.MessageDto;
import at.fhv.teama.easyticket.dto.PersonDto;
import at.fhv.teama.easyticket.dto.TicketDto;
import at.fhv.teama.easyticket.dto.VenueDto;
import at.fhv.teama.easyticket.rmi.EasyTicketService;
import at.fhv.teama.easyticket.server.program.ProgramController;
import at.fhv.teama.easyticket.server.venue.VenueController;
import at.fhv.teama.easyticket.server.venue.ticket.TicketController;
import org.springframework.context.ApplicationContext;

import javax.ejb.Stateless;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Stateless(name = "EasyTicketService")
public class EasyTicketServiceEjbImpl implements EasyTicketService {
  private String username = "no user";
  private ApplicationContext apx;

  @Override
  public Set<VenueDto> getAllVenues() {
    return SpringEjbConnector.getBean(VenueController.class).getAllVenues();
  }

  @Override
  public Set<String> getAllGenres() {
    return SpringEjbConnector.getBean(ProgramController.class).getAllGenres();
  }

  @Override
  public Set<VenueDto> searchVenue(
      String description, String genre, String artistName, LocalDateTime from, LocalDateTime to) {
    return SpringEjbConnector.getBean(VenueController.class).getAllVenuesByFilter(from, to, genre, description, artistName);
  }

  @Override
  public Set<PersonDto> getAllCustomer() {
    return null;
  }

  @Override
  public Set<TicketDto> buyTickets(Collection<TicketDto> tickets) {
    return SpringEjbConnector.getBean(TicketController.class).buyTickets(tickets);
  }

  @Override
  public Set<TicketDto> reserveTickets(Collection<TicketDto> tickets) {
    return null;
  }

  @Override
  public boolean unreserveTickets(Collection<TicketDto> tickets) {
    return SpringEjbConnector.getBean(TicketController.class).unreserveTickets(tickets);
  }

  @Override
  public void publishMessage(MessageDto message) {

  }

  @Override
  public void publishFeed(String url, String topic) {

  }

  @Override
  public Set<MessageDto> getAllUnreadMessages(String username) {
    return null;
  }

  @Override
  public void acknowledgeMessage(String id) {

  }

  @Override
  public Set<String> login(String username, String password) {
    this.apx = SpringEjbConnector.apx;
    System.out.println("logged in  as " + this.username);
    System.out.println("login as " + username);
    this.username = username;
    return new HashSet<>();
  }
}
