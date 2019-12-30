package at.fhv.teama.easyticket.server.ejb;

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
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.ejb.Stateless;
import javax.jms.JMSException;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Slf4j
@Stateless(name = "EasyTicketService")
public class EasyTicketServiceEjbImpl implements EasyTicketService {
  private void setSecurityContext(UsernamePasswordAuthenticationToken upat) {
    if (upat != null) {
      upat.setAuthenticated(false);
      SecurityContextHolder.getContext().setAuthentication(upat);
    }
  }

  private void clearSecurityContext() {
    SecurityContextHolder.clearContext();
  }

  @Override
  public Set<VenueDto> getAllVenues() {
    return SpringEjbConnector.getBean(VenueController.class).getAllVenues();
  }

  @Override
  public Set<String> getAllGenres() {
    log.info("getting genres");
    return SpringEjbConnector.getBean(ProgramController.class).getAllGenres();
  }

  @Override
  public Set<VenueDto> searchVenue(
          String description, String genre, String artistName, LocalDateTime from, LocalDateTime to) {
    log.info("searching venues");
    return SpringEjbConnector.getBean(VenueController.class).getAllVenuesByFilter(from, to, genre, description, artistName);
  }

  @Override
  public Set<PersonDto> getAllCustomer() {
    log.info("getting customers");
    return SpringEjbConnector.getBean(PersonController.class).getAllCustomer();
  }

  @Override
  public Set<TicketDto> buyTickets(Collection<TicketDto> tickets) {
    log.info("buying tickets");
    return SpringEjbConnector.getBean(TicketController.class).buyTickets(tickets);
  }

  @Override
  public Set<TicketDto> reserveTickets(Collection<TicketDto> tickets) {
    log.info("reserving tickets");
    return SpringEjbConnector.getBean(TicketController.class).reserveTickets(tickets);
  }

  @Override
  public boolean unreserveTickets(Collection<TicketDto> tickets) {
    log.info("unreserving tickets");
    return SpringEjbConnector.getBean(TicketController.class).unreserveTickets(tickets);
  }

  @Override
  public void publishMessage(MessageDto messageDto) {
    try {
      MessagingController.publishMessageToTopic("topic", messageDto.getContent());
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
    log.info("Getting all unread messages for: " + username);
    Set<MessageDto> messageDtos = new HashSet<>();
    return messageDtos;
           /*
    try {
      messageDtos = MessagingController.getMessages(username);
    } catch (JMSException e) {
      e.printStackTrace();
    }
    return messageDtos;
            */
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
    log.info("Trying to log in user: " + username);
    UsernamePasswordAuthenticationToken upat =
            new UsernamePasswordAuthenticationToken(username, password);
    setSecurityContext(upat);
    Set<String> roles = SpringEjbConnector.getBean(UserController.class).getRoles();
    clearSecurityContext();
    return roles;
  }
}
