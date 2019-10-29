package at.fhv.teama.easyticket.server.rmi;

import at.fhv.teama.easyticket.dto.VenueDto;
import at.fhv.teama.easyticket.rmi.EasyTicketService;
import at.fhv.teama.easyticket.server.venue.VenueController;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Set;

@Slf4j
@Component
@RequiredArgsConstructor
public class EasyTicketServiceImpl implements EasyTicketService {
  private final VenueController venueController;

  @Override
  public Set<VenueDto> getAllVenues() {
    return venueController.getAllVenues();
  }
}
