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

  public Set<Venue> getAllVenuesByGenere(String genre) {
    return venueRepo.findAllByProgram_GenreLikeAndDateGreaterThanEqual(genre,LocalDateTime.now());
  }

  public Set<Venue> getAllVenuesByDate(LocalDateTime date) {
    return venueRepo.findAllByDateAndDateGreaterThanEqual(date, LocalDateTime.now());
  }

  public Set<Venue> getAllVenuesbyDescription(String descritpion) {
    return venueRepo.findAllByProgram_DescriptionLikeAndDateGreaterThanEqual(descritpion, LocalDateTime.now());
  }
}
