package at.fhv.teama.easyticket.server.venue;

import at.fhv.teama.easyticket.dto.VenueDto;
import at.fhv.teama.easyticket.server.MapperContext;
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

    LocalDateTime localDateTime = LocalDateTime.now();
    Program oldProgram = new Program();
    Venue oldVenue = new Venue();
    oldVenue.setDate(localDateTime.minusDays(1));
    oldVenue.setProgram(oldProgram);
    oldVenue = venueRepo.save(oldVenue);

    Program futureprogram = new Program();
    Venue futureVenue = new Venue();
    futureVenue.setDate(localDateTime.plusMinutes(2));
    futureVenue.setProgram(futureprogram);
    futureVenue=venueRepo.save(futureVenue);

    VenueDto futureVenueDTO = venueMapper.venueToVenueDto(futureVenue,new MapperContext());
    assertEquals(Set.of(futureVenueDTO), venueController.getAllVenuesByFilter(null,null,null,null));
  }

  @Test
  public void getAllVenuesMULTIPLE() {

    LocalDateTime localDateTime = LocalDateTime.now();

    Program oldProgram = new Program();
    Venue oldVenue = new Venue();
    oldVenue.setDate(localDateTime.minusDays(1));
    oldVenue.setProgram(oldProgram);
    venueRepo.save(oldVenue);

    Program futureProgram0 = new Program();
    Venue futureVenue0 = new Venue();
    futureVenue0.setDate(localDateTime.plusMinutes(2));
    futureVenue0.setProgram(futureProgram0);
    futureVenue0 = venueRepo.save(futureVenue0);

    Program futureProgram1 = new Program();
    Venue futureVenue1 = new Venue();
    futureVenue1.setDate(localDateTime.plusDays(1));
    futureVenue1.setProgram(futureProgram1);
    futureVenue1 = venueRepo.save(futureVenue1);

    Program futureProgram2 = new Program();
    Venue futureVenue2 = new Venue();
    futureVenue2.setDate(localDateTime.plusDays(2));
    futureVenue2.setProgram(futureProgram2);
    futureVenue2 = venueRepo.save(futureVenue2);

    VenueDto futureVenue0DTO = venueMapper.venueToVenueDto(futureVenue0,new MapperContext());
    VenueDto futureVenue1DTO = venueMapper.venueToVenueDto(futureVenue1,new MapperContext());
    VenueDto futureVenue2DTO = venueMapper.venueToVenueDto(futureVenue2,new MapperContext());

    assertEquals(Set.of(futureVenue0DTO, futureVenue1DTO, futureVenue2DTO), venueController.getAllVenuesByFilter(null,null,null,null));
  }

  @Test
  public void getAllVenuesByGenre(){
    LocalDateTime localDateTime = LocalDateTime.now();
    Program oldProgram = new Program();
    oldProgram.setGenre("Theater");
    Venue oldVenue = new Venue();
    oldVenue.setDate(localDateTime.minusDays(1));
    oldVenue.setProgram(oldProgram);
    venueRepo.save(oldVenue);

    Program futureProgram0 = new Program();
    futureProgram0.setGenre("Klassik");
    Venue futureVenue0 = new Venue();
    futureVenue0.setDate(localDateTime.plusMinutes(2));
    futureVenue0.setProgram(futureProgram0);
    venueRepo.save(futureVenue0);

    Program futureProgram1 = new Program();
    futureProgram1.setGenre("Konzert");
    Venue futureVenue1 = new Venue();
    futureVenue1.setDate(localDateTime.plusDays(1));
    futureVenue1.setProgram(futureProgram1);
    venueRepo.save(futureVenue1);

    Program futureProgram2 = new Program();
    futureProgram2.setGenre("Theater");
    Venue futureVenue2 = new Venue();
    futureVenue2.setDate(localDateTime.plusDays(2));
    futureVenue2.setProgram(futureProgram2);
    futureVenue2 = venueRepo.save(futureVenue2);

    VenueDto futureVenue2DTO = venueMapper.venueToVenueDto(futureVenue2,new MapperContext());
    assertEquals(Set.of(futureVenue2DTO),venueController.getAllVenuesByFilter(null,null,"Theater",null));
  }

  @Test
  public void getAllVenuesByDateRange(){
    LocalDateTime localDateTime = LocalDateTime.now();

    Program oldProgram = new Program();
    Venue oldVenue = new Venue();
    oldVenue.setDate(localDateTime.minusDays(1));
    oldVenue.setProgram(oldProgram);
    venueRepo.save(oldVenue);

    Program futureProgram0 = new Program();
    Venue futureVenue0 = new Venue();
    futureVenue0.setDate(localDateTime.plusMinutes(2));
    futureVenue0.setProgram(futureProgram0);
    futureVenue0 = venueRepo.save(futureVenue0);

    Program futureProgram1 = new Program();
    Venue futureVenue1 = new Venue();
    futureVenue1.setDate(localDateTime.plusDays(1));
    futureVenue1.setProgram(futureProgram1);
    futureVenue1 = venueRepo.save(futureVenue1);

    Program futureProgram2 = new Program();
    Venue futureVenue2 = new Venue();
    futureVenue2.setDate(localDateTime.plusDays(2));
    futureVenue2.setProgram(futureProgram2);
    venueRepo.save(futureVenue2);

    VenueDto futureVenue0DTO = venueMapper.venueToVenueDto(futureVenue0,new MapperContext());
    VenueDto futureVenue1DTO = venueMapper.venueToVenueDto(futureVenue1,new MapperContext());
    assertEquals(Set.of(futureVenue0DTO,futureVenue1DTO),venueController.getAllVenuesByFilter(null,localDateTime.plusDays(1),null,null));
  }

  @Test
  public void getAllVenuesByDescription(){
    LocalDateTime localDateTime = LocalDateTime.now();
    Program oldProgram = new Program();
    oldProgram.setDescription("Schanensee");
    Venue oldVenue = new Venue();
    oldVenue.setDate(localDateTime.minusDays(1));
    oldVenue.setProgram(oldProgram);
    venueRepo.save(oldVenue);

    Program futureProgram0 = new Program();
    futureProgram0.setDescription("Zauberflöte");   //ö ä ü in DB?
    Venue futureVenue0 = new Venue();
    futureVenue0.setDate(localDateTime.plusMinutes(2));
    futureVenue0.setProgram(futureProgram0);
    venueRepo.save(futureVenue0);

    Program futureProgram1 = new Program();
    futureProgram1.setDescription("Moonwalk");
    Venue futureVenue1 = new Venue();
    futureVenue1.setDate(localDateTime.plusDays(1));
    futureVenue1.setProgram(futureProgram1);
    futureVenue1 = venueRepo.save(futureVenue1);

    Program futureProgram2 = new Program();
    futureProgram2.setDescription("Supertalent");
    Venue futureVenue2 = new Venue();
    futureVenue2.setDate(localDateTime.plusDays(2));
    futureVenue2.setProgram(futureProgram2);
    venueRepo.save(futureVenue2);

    VenueDto futureVenue1DTO = venueMapper.venueToVenueDto(futureVenue1,new MapperContext());
    assertEquals(Set.of(futureVenue1DTO),venueController.getAllVenuesByFilter(null,null,null,"Moonwalk"));
  }

    @Test
    public void getAllVenuesByFilter(){
        LocalDateTime localDateTime = LocalDateTime.now();
        Program oldProgram = new Program();
        oldProgram.setDescription("Schanensee");
        Venue oldVenue = new Venue();
        oldVenue.setDate(localDateTime.minusDays(1));
        oldVenue.setProgram(oldProgram);
        venueRepo.save(oldVenue);

        Program futureProgram0 = new Program();
        futureProgram0.setDescription("Zauberflöte");   //ö ä ü in DB?
        Venue futureVenue0 = new Venue();
        futureVenue0.setDate(localDateTime.plusMinutes(2));
        futureVenue0.setProgram(futureProgram0);
        venueRepo.save(futureVenue0);

        Program futureProgram1 = new Program();
        futureProgram1.setDescription("Moonwalk");
        Venue futureVenue1 = new Venue();
        futureVenue1.setDate(localDateTime.plusDays(1));
        futureVenue1.setProgram(futureProgram1);
        venueRepo.save(futureVenue1);

        Program futureProgram2 = new Program();
        futureProgram2.setDescription("Supertalent");
        Venue futureVenue2 = new Venue();
        futureVenue2.setDate(localDateTime.plusDays(2));
        futureVenue2.setProgram(futureProgram2);
        futureVenue2 = venueRepo.save(futureVenue2);

        VenueDto futureVenue2DTO = venueMapper.venueToVenueDto(futureVenue2,new MapperContext());
        assertEquals(Set.of(futureVenue2DTO),venueController.getAllVenuesByFilter(null,null,null,"Supertalent"));
    }
}
