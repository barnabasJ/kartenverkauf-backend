package at.fhv.teama.easyticket.server.venue;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.Set;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class VenueServiceTest {
  @Autowired private VenueRepository venueRepo;
  @Autowired private VenueService venueService;

  private Venue futureVenue;
  private Venue oldVenue;

  @Before
  public void setup() {
    oldVenue = new Venue();
    oldVenue.setDate(LocalDateTime.now().minusDays(1));
    futureVenue = new Venue();
    futureVenue.setDate(LocalDateTime.now().plusDays(1));
    oldVenue = venueRepo.save(oldVenue);
    futureVenue = venueRepo.save(futureVenue);
  }

  @Test
  public void getAllVenues() {
    assertEquals(Set.of(futureVenue), venueService.getAllVenues());
  }
}
