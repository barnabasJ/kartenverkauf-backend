package at.fhv.teama.easyticket.server.ejb;

import at.fhv.teama.easyticket.dto.MessageDto;
import at.fhv.teama.easyticket.dto.PersonDto;
import at.fhv.teama.easyticket.dto.TicketDto;
import at.fhv.teama.easyticket.dto.VenueDto;
import at.fhv.teama.easyticket.rmi.EasyTicketService;

import javax.ejb.Stateless;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Stateless(name = "EasyTicketService")
public class EasyTicketServiceEjbImpl implements EasyTicketService {
  @Override
  public Set<VenueDto> getAllVenues() {
    return null;
  }

  @Override
  public Set<String> getAllGenres() {
    return null;
  }

  @Override
  public Set<VenueDto> searchVenue(
      String description, String genre, String artistName, LocalDateTime from, LocalDateTime to) {
    return null;
  }

  @Override
  public Set<PersonDto> getAllCustomer() {
    return null;
  }

  @Override
  public Set<TicketDto> buyTickets(Collection<TicketDto> tickets) {
    return null;
  }

  @Override
  public Set<TicketDto> reserveTickets(Collection<TicketDto> tickets) {
    return null;
  }

  @Override
  public boolean unreserveTickets(Collection<TicketDto> tickets) {
    return false;
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
    return new HashSet<>();
  }
}
