package at.fhv.teama.easyticket.server.venue;

import at.fhv.teama.easyticket.dto.VenueDto;
import at.fhv.teama.easyticket.server.program.Program;
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
  @Autowired private VenueController venueController;
  @Autowired private VenueMapper venueMapper;


  private Program futureProgram;
  private Program oldProgram;

  @Before
  public void setup() {

    /*
    futureProgram = new Program();
    futureProgram.setDescription("Hell Cat");
    futureProgram.setGenre("Rock");

    oldProgram = new Program();
    oldProgram.setDescription("Mama Mia");
    oldProgram.setGenre("Theater");*/
  }

  @Test
  public void getAllVenues() {
    Venue futureVenue;
    Venue oldVenue;
    oldVenue = new Venue();
    oldVenue.setDate(LocalDateTime.now().minusDays(1));
    futureVenue = new Venue();
    futureVenue.setDate(LocalDateTime.now().plusDays(1));
    VenueDto futureVenuDTO = venueMapper.venueToVenueDto(futureVenue);
    oldVenue = venueRepo.save(oldVenue);
    futureVenue = venueRepo.save(futureVenue);
    assertEquals(Set.of(futureVenuDTO), venueController.getAllVenues());
    venueRepo.delete(oldVenue);
    venueRepo.delete(futureVenue);
  }
}
