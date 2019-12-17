package at.fhv.teama.easyticket.server.venue;

import at.fhv.teama.easyticket.dto.VenueDto;
import at.fhv.teama.easyticket.server.mapping.MapperContext;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@Controller
@RequiredArgsConstructor
public class VenueController {
  private final VenueService venueService;
  private final VenueMapper venueMapper;

  //@Transactional(readOnly = true)
  @GetMapping("/venues/search")
  public Set<VenueDto> getAllVenuesByFilter(
          @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime localDateTimeStart,
          @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime localDateTimeEnd,
          @RequestParam(required = false) String genre,
          @RequestParam(required = false) String description,
          @RequestParam(required = false) String artist) {
    genre = sanitizeStringInput(genre);
    description = sanitizeStringInput(description);
    artist = sanitizeStringInput(artist);
    localDateTimeStart = sanitizeDateStart(localDateTimeStart);
    localDateTimeEnd = sanitizeDateEnd(localDateTimeEnd);
    return venueService
        .getAllVenusByFilter(localDateTimeStart, localDateTimeEnd, genre, description, artist)
        .stream()
        .map(v -> venueMapper.venueToVenueDto(v, new MapperContext()))
        .collect(Collectors.toSet());
  }

  private String sanitizeStringInput(String input) {
    String sanitizedString = input != null ? input.trim() : "";
    if (sanitizedString.isBlank()) {
      return "%";
    }
    return "%" + input + "%";
  }

  private LocalDateTime sanitizeDateStart(LocalDateTime input) {
    if (input == null) {
      return LocalDateTime.now();
    }
    return input;
  }

  private LocalDateTime sanitizeDateEnd(LocalDateTime input) {
    if (input == null) {
      return LocalDateTime.now().plusMonths(12);
    }
    return input;
  }

  public Set<VenueDto> getAllVenues() {
    return getAllVenuesByFilter(null, null, null, null, null);
  }
}
