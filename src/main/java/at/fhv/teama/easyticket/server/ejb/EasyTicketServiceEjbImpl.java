package at.fhv.teama.easyticket.server.ejb;

import at.fhv.teama.easyticket.dto.MessageDto;
import at.fhv.teama.easyticket.dto.PersonDto;
import at.fhv.teama.easyticket.dto.TicketDto;
import at.fhv.teama.easyticket.dto.VenueDto;
import at.fhv.teama.easyticket.rmi.EasyTicketService;
import at.fhv.teama.easyticket.server.messaging.MessagingController;
import at.fhv.teama.easyticket.server.person.PersonController;
import at.fhv.teama.easyticket.server.program.ProgramController;
import at.fhv.teama.easyticket.server.venue.VenueController;
import at.fhv.teama.easyticket.server.venue.ticket.TicketController;
import org.springframework.context.ApplicationContext;

import javax.ejb.Stateless;
import javax.jms.JMSException;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Stateless(name = "EasyTicketService")
public class EasyTicketServiceEjbImpl implements EasyTicketService {
  private String username = "no user";

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
    return SpringEjbConnector.getBean(PersonController.class).getAllCustomer();
  }

  @Override
  public Set<TicketDto> buyTickets(Collection<TicketDto> tickets) {
    return SpringEjbConnector.getBean(TicketController.class).buyTickets(tickets);
  }

  @Override
  public Set<TicketDto> reserveTickets(Collection<TicketDto> tickets) {
    return SpringEjbConnector.getBean(TicketController.class).reserveTickets(tickets);
  }

  @Override
  public boolean unreserveTickets(Collection<TicketDto> tickets) {
    return SpringEjbConnector.getBean(TicketController.class).unreserveTickets(tickets);
  }

  @Override
  public void publishMessage(MessageDto messageDto) {
    try {
      SpringEjbConnector.getBean(MessagingController.class).publishMessageToTopic("topic", messageDto.getContent());
    } catch (JMSException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void publishFeed(String url, String topic) {
    SpringEjbConnector.getBean(MessagingController.class).publishFeed(url,topic);
  }

  @Override
  public Set<MessageDto> getAllUnreadMessages(String username) {

    Set<MessageDto> messageDtos = new HashSet<>();
    try {
      messageDtos = SpringEjbConnector.getBean(MessagingController.class).getMessages("topic", username);
    } catch (JMSException e) {
      e.printStackTrace();
    }
    return messageDtos;
  }

  @Override
  public void acknowledgeMessage(String messageText) {

    try {
      SpringEjbConnector.getBean(MessagingController.class).acknowledgeMessage("client1", messageText);
    } catch (JMSException e) {
      e.printStackTrace();
    }
  }

  @Override
  public Set<String> login(String username, String password) {
    System.out.println("logged in  as " + this.username);
    System.out.println("login as " + username);
    this.username = username;
    return new HashSet<>();
  }
}
