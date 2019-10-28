package at.fhv.teama.easyticket.server.venue;

import at.fhv.teama.easyticket.dto.VenueDto;
import at.fhv.teama.easyticket.server.MapperContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class VenueController {
  private final VenueService venueService;
  private final VenueMapper venueMapper;

  @Transactional(readOnly = true)
  public Set<VenueDto> getAllVenues() {
    return venueService.getAllVenues().stream()
        .map(v -> venueMapper.venueToVenueDto(v, new MapperContext()))
        .collect(Collectors.toSet());
  }

  public Set<VenueDto> getAllVenuesByGenre(String genre) {
    return venueService.getAllVenuesByGenere(genre).stream()
            .map(venueMapper::venueToVenueDto)
            .collect(Collectors.toSet());
  }

  public Set<VenueDto> getAllVenuesByDate(LocalDateTime date) {
    return venueService.getAllVenuesByDate(date).stream()
            .map(venueMapper::venueToVenueDto)
            .collect(Collectors.toSet());
  }
}
