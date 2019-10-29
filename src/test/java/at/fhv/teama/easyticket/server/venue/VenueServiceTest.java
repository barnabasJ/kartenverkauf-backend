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

  @Test
  public void getAllVenues() {

    Venue oldVenue = new Venue();
    oldVenue.setDate(LocalDateTime.now().minusDays(1));
    Venue futureVenue = new Venue();
    futureVenue.setDate(LocalDateTime.now().plusDays(1));
    VenueDto futureVenueDTO = venueMapper.venueToVenueDto(futureVenue);
    venueRepo.save(oldVenue);
    venueRepo.save(futureVenue);
    futureVenueDTO.setId((long) 2);
    assertEquals(Set.of(futureVenueDTO), venueController.getAllVenues());
    venueRepo.deleteAll();
  }

  @Test
  public void getAllVenuesByGenre(){
    Program oldProgram = new Program();
    oldProgram.setGenre("Theater");
    Venue oldVenue = new Venue();
    oldVenue.setDate(LocalDateTime.now().minusDays(1));
    oldVenue.setProgram(oldProgram);
    venueRepo.save(oldVenue);

    Program futureProgram0 = new Program();
    futureProgram0.setGenre("Klassik");
    Venue futureVenue0 = new Venue();
    futureVenue0.setDate(LocalDateTime.now());
    futureVenue0.setProgram(futureProgram0);
    venueRepo.save(futureVenue0);

    Program futureProgram1 = new Program();
    futureProgram1.setGenre("Konzert");
    Venue futureVenue1 = new Venue();
    futureVenue1.setDate(LocalDateTime.now().plusDays(1));
    futureVenue1.setProgram(futureProgram1);
    venueRepo.save(futureVenue1);

    Program futureProgram2 = new Program();
    futureProgram2.setGenre("Theater");
    Venue futureVenue2 = new Venue();
    futureVenue2.setDate(LocalDateTime.now().plusDays(2));
    futureVenue2.setProgram(futureProgram2);
    venueRepo.save(futureVenue2);

    VenueDto futureVenue2DTO = venueMapper.venueToVenueDto(futureVenue2);
    assertEquals(Set.of(futureVenue2DTO),venueController.getAllVenuesByGenre("Theater"));
    venueRepo.deleteAll();
  }
}
