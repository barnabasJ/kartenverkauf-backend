package at.fhv.teama.easyticket.server.venue;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class VenueService {
  private final VenueRepository venueRepo;

  public Set<Venue> getAllVenues() {
    return venueRepo.findAllByDateGreaterThanEqual(LocalDateTime.now());
  }
}
