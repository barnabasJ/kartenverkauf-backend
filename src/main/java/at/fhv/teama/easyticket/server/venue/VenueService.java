package at.fhv.teama.easyticket.server.venue;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class VenueService {
  private final VenueRepository venueRepo;

  public Set<Venue> getAllVenusByFilter(
      LocalDateTime dateTime,
      LocalDateTime dateTimeNow,
      String genre,
      String description,
      String artist) {
    return venueRepo
        .findAllByDateGreaterThanEqualAndDateLessThanEqualAndProgram_GenreLikeAndProgram_DescriptionLikeAndProgram_Artists_NameLike(
            dateTime, dateTimeNow, genre, description, artist);
  }
}
