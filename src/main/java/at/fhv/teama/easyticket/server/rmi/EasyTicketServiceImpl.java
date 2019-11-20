package at.fhv.teama.easyticket.server.rmi;

import at.fhv.teama.easyticket.dto.PersonDto;
import at.fhv.teama.easyticket.dto.TicketDto;
import at.fhv.teama.easyticket.dto.VenueDto;
import at.fhv.teama.easyticket.rmi.EasyTicketService;
import at.fhv.teama.easyticket.server.mapping.MapperContext;
import at.fhv.teama.easyticket.server.person.PersonMapper;
import at.fhv.teama.easyticket.server.person.PersonRepo;
import at.fhv.teama.easyticket.server.program.ProgramController;
import at.fhv.teama.easyticket.server.venue.VenueController;
import at.fhv.teama.easyticket.server.venue.ticket.TicketController;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.security.RolesAllowed;
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
  public Boolean unreserveTickets(Collection<TicketDto> tickets) {
    return ticketController.unreserveTickets(tickets);
  }

  @Override
  @RolesAllowed("USER")
  public boolean login(String username, String password) {
    log.info("Trying to log in as " + username);
    return true;
  }
}
