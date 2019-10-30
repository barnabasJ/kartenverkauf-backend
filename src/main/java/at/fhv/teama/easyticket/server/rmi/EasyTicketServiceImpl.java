package at.fhv.teama.easyticket.server.rmi;

import at.fhv.teama.easyticket.dto.PersonDto;
import at.fhv.teama.easyticket.dto.TicketDto;
import at.fhv.teama.easyticket.dto.VenueDto;
import at.fhv.teama.easyticket.rmi.EasyTicketService;
import at.fhv.teama.easyticket.server.program.ProgramRepository;
import at.fhv.teama.easyticket.server.venue.VenueController;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Slf4j
@Component
@RequiredArgsConstructor
public class EasyTicketServiceImpl implements EasyTicketService {
  private final VenueController venueController;

  // TODO remove
  private final ProgramRepository programRepo;

  @Override
  public Set<VenueDto> getAllVenues() {
    return venueController.getAllVenues();
  }

  @Override
  public Set<String> getAllGenres() {
    return programRepo.getAllGenres();
  }

  @Override
  public Set<VenueDto> searchVenue(String description, String genre, String artistName, LocalDateTime from, LocalDateTime to) {
    return getAllVenues();
  }

  @Override
  public Set<PersonDto> getAllCustomer() {
    return new HashSet<>();
  }

  @Override
  public Set<TicketDto> buyTickets(Collection<TicketDto> tickets) {
    return new HashSet<>();
  }
}
