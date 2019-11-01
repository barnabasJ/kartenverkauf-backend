package at.fhv.teama.easyticket.server.venue;

import at.fhv.teama.easyticket.dto.VenueDto;
import at.fhv.teama.easyticket.server.program.Program;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class VenueServiceTest {
  @Autowired private VenueRepository venueRepo;
  @Autowired private VenueController venueController;
  @Autowired private VenueMapper venueMapper;

  @Test
  public void getAllVenuesONE() {

    Venue oldVenue = new Venue();
    oldVenue.setDate(LocalDateTime.now().minusDays(1));
    oldVenue = venueRepo.save(oldVenue);

    Venue futureVenue = new Venue();
    futureVenue.setDate(LocalDateTime.now().plusMinutes(2));
    futureVenue=venueRepo.save(futureVenue);

    VenueDto futureVenueDTO = venueMapper.venueToVenueDto(futureVenue);
    assertEquals(Set.of(futureVenueDTO), venueController.getAllVenues());
  }

  @Test
  public void getAllVenuesMULTIPLE() {

    Venue oldVenue = new Venue();
    oldVenue.setDate(LocalDateTime.now().minusDays(1));
    venueRepo.save(oldVenue);

    Venue futureVenue0 = new Venue();
    futureVenue0.setDate(LocalDateTime.now().plusMinutes(2));
    venueRepo.save(futureVenue0);

    Venue futureVenue1 = new Venue();
    futureVenue1.setDate(LocalDateTime.now().plusDays(1));
    venueRepo.save(futureVenue1);

    Venue futureVenue2 = new Venue();
    futureVenue2.setDate(LocalDateTime.now().plusDays(2));
    venueRepo.save(futureVenue2);

    VenueDto futureVenue0DTO = venueMapper.venueToVenueDto(futureVenue0);
    VenueDto futureVenue1DTO = venueMapper.venueToVenueDto(futureVenue1);
    VenueDto futureVenue2DTO = venueMapper.venueToVenueDto(futureVenue2);

    //futureVenueDTO.setId((long) 2);
    assertEquals(Set.of(futureVenue0DTO, futureVenue1DTO, futureVenue2DTO), venueController.getAllVenues());
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
    futureVenue0.setDate(LocalDateTime.now().plusMinutes(2));
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
  }

  @Test
  public void getAllVenuesByDate(){
    Venue oldVenue = new Venue();
    oldVenue.setDate(LocalDateTime.now().minusDays(1));
    venueRepo.save(oldVenue);

    Venue futureVenue0 = new Venue();
    futureVenue0.setDate(LocalDateTime.now().plusMinutes(2));
    venueRepo.save(futureVenue0);

    Venue futureVenue1 = new Venue();
    futureVenue1.setDate(LocalDateTime.now().plusDays(1));
    venueRepo.save(futureVenue1);

    Venue futureVenue2 = new Venue();
    futureVenue2.setDate(LocalDateTime.now().plusDays(2));
    venueRepo.save(futureVenue2);

    VenueDto futureVenue1DTO = venueMapper.venueToVenueDto(futureVenue1);
    assertEquals(Set.of(futureVenue1DTO),venueController.getAllVenuesByDate(LocalDateTime.now().plusDays(1)));
  }

  @Test
  public void getAllVenuesByDescription(){
    Program oldProgram = new Program();
    oldProgram.setDescription("Schanensee");
    Venue oldVenue = new Venue();
    oldVenue.setDate(LocalDateTime.now().minusDays(1));
    oldVenue.setProgram(oldProgram);
    venueRepo.save(oldVenue);

    Program futureProgram0 = new Program();
    futureProgram0.setDescription("Zauberflöte");   //ö ä ü in DB?
    Venue futureVenue0 = new Venue();
    futureVenue0.setDate(LocalDateTime.now().plusMinutes(2));
    futureVenue0.setProgram(futureProgram0);
    venueRepo.save(futureVenue0);

    Program futureProgram1 = new Program();
    futureProgram1.setDescription("Moonwalk");
    Venue futureVenue1 = new Venue();
    futureVenue1.setDate(LocalDateTime.now().plusDays(1));
    futureVenue1.setProgram(futureProgram1);
    venueRepo.save(futureVenue1);

    Program futureProgram2 = new Program();
    futureProgram2.setDescription("Supertalent");
    Venue futureVenue2 = new Venue();
    futureVenue2.setDate(LocalDateTime.now().plusDays(2));
    futureVenue2.setProgram(futureProgram2);
    venueRepo.save(futureVenue2);

    VenueDto futureVenue1DTO = venueMapper.venueToVenueDto(futureVenue1);
    assertEquals(Set.of(futureVenue1DTO),venueController.getAllVenuesByDescription("Moonwalk"));
  }
}
