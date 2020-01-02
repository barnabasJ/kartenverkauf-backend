package at.fhv.teama.easyticket.server.rmi;

import at.fhv.teama.easyticket.dto.MessageDto;
import at.fhv.teama.easyticket.dto.PersonDto;
import at.fhv.teama.easyticket.dto.TicketDto;
import at.fhv.teama.easyticket.dto.VenueDto;
import at.fhv.teama.easyticket.rmi.EasyTicketService;
import at.fhv.teama.easyticket.server.messaging.MessagingController;
import at.fhv.teama.easyticket.server.person.PersonController;
import at.fhv.teama.easyticket.server.program.ProgramController;
import at.fhv.teama.easyticket.server.user.UserController;
import at.fhv.teama.easyticket.server.venue.VenueController;
import at.fhv.teama.easyticket.server.venue.ticket.TicketController;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;

import javax.jms.JMSException;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Slf4j
@Controller
@RequiredArgsConstructor
public class EasyTicketServiceImpl implements EasyTicketService {
  private final VenueController venueController;
  private final TicketController ticketController;
  private final ProgramController programController;
  private final PersonController personController;
  private final UserController userController;

  @Override
  public Set<VenueDto> getAllVenues() {
    return venueController.getAllVenuesByFilter(null, null, null, null, null);
  }

  @Override
  public Set<String> getAllGenres() {
    return programController.getAllGenres();
  }

  @Override
  public Set<VenueDto> searchVenue(
      String description, String genre, String artistName, LocalDateTime from, LocalDateTime to) {
    return venueController.getAllVenuesByFilter(from, to, genre, description, artistName);
  }

  @Override
  public Set<PersonDto> getAllCustomer() {
    return personController.getAllCustomer();
  }

  @Override
  public Set<TicketDto> buyTickets(Collection<TicketDto> tickets) {
    return ticketController.buyTickets(tickets);
  }

  @Override
  public Set<TicketDto> reserveTickets(Collection<TicketDto> tickets) {
    return ticketController.reserveTickets(tickets);
  }

  @Override
  public boolean unreserveTickets(Collection<TicketDto> tickets) {
    return ticketController.unreserveTickets(tickets);
  }

  @Override
  public void publishMessage(MessageDto messageDto) {
    try {
      MessagingController.publishMessageToTopic(messageDto.getTopic(), messageDto.getContent());
    } catch (JMSException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void publishFeed(String url, String topic) {
    MessagingController.publishFeed(url, topic);
  }

  @Override
  public Set<MessageDto> getAllUnreadMessages(String username) {
    Set<MessageDto> messageDtos = new HashSet<>();
    try {
      messageDtos = MessagingController.getMessages(username);
    } catch (JMSException e) {
      e.printStackTrace();
    }
    return messageDtos;
  }

  @Override
  public void acknowledgeMessage(MessageDto messageDto, String username) {
    try {
      MessagingController.acknowledgeMessage(username, messageDto.getContent());
    } catch (JMSException e) {
      e.printStackTrace();
    }
  }

  @Override
  public Set<String> login(String username, String password) {
    log.info("Logged in as" + username);
    return userController.getRoles();
  }
}
