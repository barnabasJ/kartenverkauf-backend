package at.fhv.teama.easyticket.server.venue;

import at.fhv.teama.easyticket.dto.VenueDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class VenueController {
  private final VenueService venueService;
  private final VenueMapper venueMapper;

  public Set<VenueDto> getAllVenues() {
    return venueService.getAllVenues().stream()
        .map(venueMapper::venueToVenueDto)
        .collect(Collectors.toSet());
  }
}
