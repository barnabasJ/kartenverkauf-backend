package at.fhv.teama.easyticket.server.rmi;

import at.fhv.teama.easyticket.dto.MessageDto;
import at.fhv.teama.easyticket.dto.PersonDto;
import at.fhv.teama.easyticket.dto.TicketDto;
import at.fhv.teama.easyticket.dto.VenueDto;
import at.fhv.teama.easyticket.rmi.EasyTicketService;
import at.fhv.teama.easyticket.server.mapping.MapperContext;
import at.fhv.teama.easyticket.server.messaging.MessagingController;
import at.fhv.teama.easyticket.server.person.PersonMapper;
import at.fhv.teama.easyticket.server.person.PersonRepo;
import at.fhv.teama.easyticket.server.program.ProgramController;
import at.fhv.teama.easyticket.server.venue.VenueController;
import at.fhv.teama.easyticket.server.venue.ticket.TicketController;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.annotation.security.RolesAllowed;
import javax.jms.JMSException;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Slf4j
@Component
@RequiredArgsConstructor
public class EasyTicketServiceImpl implements EasyTicketService {
  private final VenueController venueController;
  private final TicketController ticketController;
  private final ProgramController programController;

  // TODO remove
  private final PersonRepo personRepo;
  private final PersonMapper personMapper;

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
    return StreamSupport.stream(personRepo.findAll().spliterator(), false)
        .map(p -> personMapper.personToPersonDto(p, new MapperContext()))
        .collect(Collectors.toSet());
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
    // Todo change interface to "public void publishMessage(String topicName, MessageDto messageDto)"
    try {
      MessagingController.publishMessageToTopic("topic", messageDto.getContent());
    } catch (JMSException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void publishFeed(String url, String topic) {
    MessagingController.publishFeed(url,topic);
  }

  @Override
  public Set<MessageDto> getAllUnreadMessages(String username) {
    // Todo change signature to "public Set<MessageDto> getAllUnreadMessages(String topicName, String userName)"
    Set<MessageDto> messageDtos = new HashSet<>();
    try {
      messageDtos = MessagingController.getMessages("topic", username);
    } catch (JMSException e) {
      e.printStackTrace();
    }
    return messageDtos;
  }

  @Override
  public void acknowledgeMessage(String messageText) {
    // Todo change signature to "public void acknowledgeMessage(MessageDto messageDto, String userName)"

    try {
      MessagingController.acknowledgeMessage("client1", messageText);
    } catch (JMSException e) {
      e.printStackTrace();
    }
  }

  @Override
  @RolesAllowed("USER")
  public Set<String> login(String username, String password) {
    log.info("Logged in as" + username);
    Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    if (principal instanceof UserDetails) {
      return ((UserDetails) principal)
              .getAuthorities().stream().map(Object::toString).collect(Collectors.toSet());
    }
    return new HashSet<>();
  }
}
