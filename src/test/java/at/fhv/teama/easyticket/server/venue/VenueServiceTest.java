package at.fhv.teama.easyticket.server.venue;

import at.fhv.teama.easyticket.dto.VenueDto;
import at.fhv.teama.easyticket.server.MapperContext;
import at.fhv.teama.easyticket.server.person.Artist;
import at.fhv.teama.easyticket.server.program.Program;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashSet;
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
    Artist artist = new Artist("SomeStar");
    oldProgram.setArtists(new HashSet<>(Arrays.asList(artist)));
    Venue oldVenue = new Venue();
    oldVenue.setDate(localDateTime.minusDays(1));
    oldVenue.setProgram(oldProgram);
    oldVenue = venueRepo.save(oldVenue);

    Program futureprogram = new Program();
    futureprogram.setArtists(new HashSet<>(Arrays.asList(artist)));
    Venue futureVenue = new Venue();
    futureVenue.setDate(localDateTime.plusMinutes(2));
    futureVenue.setProgram(futureprogram);
    futureVenue = venueRepo.save(futureVenue);

    VenueDto futureVenueDTO = venueMapper.venueToVenueDto(futureVenue, new MapperContext());
    assertEquals(
        Set.of(futureVenueDTO), venueController.getAllVenuesByFilter(null, null, null, null, null));
  }

  @Test
  public void getAllVenuesMULTIPLE() {

    LocalDateTime localDateTime = LocalDateTime.now();

    Program oldProgram = new Program();
    Artist artist = new Artist("SomeStar");
    oldProgram.setArtists(new HashSet<>(Arrays.asList(artist)));
    Venue oldVenue = new Venue();
    oldVenue.setDate(localDateTime.minusDays(1));
    oldVenue.setProgram(oldProgram);
    venueRepo.save(oldVenue);

    Program futureProgram0 = new Program();
    futureProgram0.setArtists(new HashSet<>(Arrays.asList(artist)));
    Venue futureVenue0 = new Venue();
    futureVenue0.setDate(localDateTime.plusMinutes(2));
    futureVenue0.setProgram(futureProgram0);
    futureVenue0 = venueRepo.save(futureVenue0);

    Program futureProgram1 = new Program();
    futureProgram1.setArtists(new HashSet<>(Arrays.asList(artist)));
    Venue futureVenue1 = new Venue();
    futureVenue1.setDate(localDateTime.plusDays(1));
    futureVenue1.setProgram(futureProgram1);
    futureVenue1 = venueRepo.save(futureVenue1);

    Program futureProgram2 = new Program();
    futureProgram2.setArtists(new HashSet<>(Arrays.asList(artist)));
    Venue futureVenue2 = new Venue();
    futureVenue2.setDate(localDateTime.plusDays(2));
    futureVenue2.setProgram(futureProgram2);
    futureVenue2 = venueRepo.save(futureVenue2);

    VenueDto futureVenue0DTO = venueMapper.venueToVenueDto(futureVenue0, new MapperContext());
    VenueDto futureVenue1DTO = venueMapper.venueToVenueDto(futureVenue1, new MapperContext());
    VenueDto futureVenue2DTO = venueMapper.venueToVenueDto(futureVenue2, new MapperContext());

    assertEquals(
        Set.of(futureVenue0DTO, futureVenue1DTO, futureVenue2DTO),
        venueController.getAllVenuesByFilter(null, null, null, null, null));
  }

  @Test
  public void getAllVenuesByGenre() {
    LocalDateTime localDateTime = LocalDateTime.now();
    Program oldProgram = new Program();
    Artist artist = new Artist("SomeStar");
    oldProgram.setArtists(new HashSet<>(Arrays.asList(artist)));
    oldProgram.setGenre("Theater");
    Venue oldVenue = new Venue();
    oldVenue.setDate(localDateTime.minusDays(1));
    oldVenue.setProgram(oldProgram);
    venueRepo.save(oldVenue);

    Program futureProgram0 = new Program();
    futureProgram0.setArtists(new HashSet<>(Arrays.asList(artist)));
    futureProgram0.setGenre("Klassik");
    Venue futureVenue0 = new Venue();
    futureVenue0.setDate(localDateTime.plusMinutes(2));
    futureVenue0.setProgram(futureProgram0);
    venueRepo.save(futureVenue0);

    Program futureProgram1 = new Program();
    futureProgram1.setGenre("Konzert");
    futureProgram1.setArtists(new HashSet<>(Arrays.asList(artist)));
    Venue futureVenue1 = new Venue();
    futureVenue1.setDate(localDateTime.plusDays(1));
    futureVenue1.setProgram(futureProgram1);
    venueRepo.save(futureVenue1);

    Program futureProgram2 = new Program();
    futureProgram2.setArtists(new HashSet<>(Arrays.asList(artist)));
    futureProgram2.setGenre("Theater");
    Venue futureVenue2 = new Venue();
    futureVenue2.setDate(localDateTime.plusDays(2));
    futureVenue2.setProgram(futureProgram2);
    futureVenue2 = venueRepo.save(futureVenue2);

    VenueDto futureVenue2DTO = venueMapper.venueToVenueDto(futureVenue2, new MapperContext());
    assertEquals(
        Set.of(futureVenue2DTO),
        venueController.getAllVenuesByFilter(null, null, "Theater", null, null));
  }

  @Test
  public void getAllVenuesByDateRange() {
    LocalDateTime localDateTime = LocalDateTime.now();

    Program oldProgram = new Program();
    Artist artist = new Artist("SomeStar");
    oldProgram.setArtists(new HashSet<>(Arrays.asList(artist)));
    Venue oldVenue = new Venue();
    oldVenue.setDate(localDateTime.minusDays(1));
    oldVenue.setProgram(oldProgram);
    venueRepo.save(oldVenue);

    Program futureProgram0 = new Program();
    futureProgram0.setArtists(new HashSet<>(Arrays.asList(artist)));
    Venue futureVenue0 = new Venue();
    futureVenue0.setDate(localDateTime.plusMinutes(2));
    futureVenue0.setProgram(futureProgram0);
    futureVenue0 = venueRepo.save(futureVenue0);

    Program futureProgram1 = new Program();
    futureProgram1.setArtists(new HashSet<>(Arrays.asList(artist)));
    Venue futureVenue1 = new Venue();
    futureVenue1.setDate(localDateTime.plusDays(1));
    futureVenue1.setProgram(futureProgram1);
    futureVenue1 = venueRepo.save(futureVenue1);

    Program futureProgram2 = new Program();
    futureProgram2.setArtists(new HashSet<>(Arrays.asList(artist)));
    Venue futureVenue2 = new Venue();
    futureVenue2.setDate(localDateTime.plusDays(2));
    futureVenue2.setProgram(futureProgram2);
    venueRepo.save(futureVenue2);

    VenueDto futureVenue0DTO = venueMapper.venueToVenueDto(futureVenue0, new MapperContext());
    VenueDto futureVenue1DTO = venueMapper.venueToVenueDto(futureVenue1, new MapperContext());
    assertEquals(
        Set.of(futureVenue0DTO, futureVenue1DTO),
        venueController.getAllVenuesByFilter(null, localDateTime.plusDays(1), null, null, null));
  }

  @Test
  public void getAllVenuesByDescription() {
    LocalDateTime localDateTime = LocalDateTime.now();
    Program oldProgram = new Program();
    Artist artist = new Artist("SomeStar");
    oldProgram.setArtists(new HashSet<>(Arrays.asList(artist)));
    oldProgram.setDescription("Schanensee");
    Venue oldVenue = new Venue();
    oldVenue.setDate(localDateTime.minusDays(1));
    oldVenue.setProgram(oldProgram);
    venueRepo.save(oldVenue);

    Program futureProgram0 = new Program();
    futureProgram0.setDescription("Zauberflöte"); // ö ä ü in DB?
    futureProgram0.setArtists(new HashSet<>(Arrays.asList(artist)));
    Venue futureVenue0 = new Venue();
    futureVenue0.setDate(localDateTime.plusMinutes(2));
    futureVenue0.setProgram(futureProgram0);
    venueRepo.save(futureVenue0);

    Program futureProgram1 = new Program();
    futureProgram1.setDescription("Moonwalk");
    futureProgram1.setArtists(new HashSet<>(Arrays.asList(artist)));
    Venue futureVenue1 = new Venue();
    futureVenue1.setDate(localDateTime.plusDays(1));
    futureVenue1.setProgram(futureProgram1);
    futureVenue1 = venueRepo.save(futureVenue1);

    Program futureProgram2 = new Program();
    futureProgram2.setArtists(new HashSet<>(Arrays.asList(artist)));
    futureProgram2.setDescription("Supertalent");
    Venue futureVenue2 = new Venue();
    futureVenue2.setDate(localDateTime.plusDays(2));
    futureVenue2.setProgram(futureProgram2);
    venueRepo.save(futureVenue2);

    VenueDto futureVenue1DTO = venueMapper.venueToVenueDto(futureVenue1, new MapperContext());
    assertEquals(
        Set.of(futureVenue1DTO),
        venueController.getAllVenuesByFilter(null, null, null, "Moonwalk", null));
  }

  @Test
  public void getAllVenuesByFilter() {
    LocalDateTime localDateTime = LocalDateTime.now();
    Program oldProgram = new Program();
    Artist artist = new Artist("SomeStar");
    oldProgram.setArtists(new HashSet<>(Arrays.asList(artist)));
    oldProgram.setDescription("Schanensee");
    Venue oldVenue = new Venue();
    oldVenue.setDate(localDateTime.minusDays(1));
    oldVenue.setProgram(oldProgram);
    venueRepo.save(oldVenue);

    Program futureProgram0 = new Program();
    futureProgram0.setDescription("Zauberflöte"); // ö ä ü in DB?
    futureProgram0.setArtists(new HashSet<>(Arrays.asList(artist)));
    Venue futureVenue0 = new Venue();
    futureVenue0.setDate(localDateTime.plusMinutes(2));
    futureVenue0.setProgram(futureProgram0);
    venueRepo.save(futureVenue0);

    Program futureProgram1 = new Program();
    futureProgram1.setDescription("Moonwalk");
    futureProgram1.setArtists(new HashSet<>(Arrays.asList(artist)));
    Venue futureVenue1 = new Venue();
    futureVenue1.setDate(localDateTime.plusDays(1));
    futureVenue1.setProgram(futureProgram1);
    venueRepo.save(futureVenue1);

    Program futureProgram2 = new Program();
    futureProgram2.setDescription("Supertalent");
    futureProgram2.setArtists(new HashSet<>(Arrays.asList(artist)));
    Venue futureVenue2 = new Venue();
    futureVenue2.setDate(localDateTime.plusDays(2));
    futureVenue2.setProgram(futureProgram2);
    futureVenue2 = venueRepo.save(futureVenue2);

    VenueDto futureVenue2DTO = venueMapper.venueToVenueDto(futureVenue2, new MapperContext());
    assertEquals(
        Set.of(futureVenue2DTO),
        venueController.getAllVenuesByFilter(null, null, null, "Supertalent", null));
  }

  @Test
  public void getAllVenuesByArtist() {
    LocalDateTime localDateTime = LocalDateTime.now();
    Program oldProgram = new Program();
    Artist artist = new Artist("SomeStar");
    oldProgram.setArtists(new HashSet<>(Arrays.asList(artist)));
    oldProgram.setDescription("Schanensee");
    Venue oldVenue = new Venue();
    oldVenue.setDate(localDateTime.minusDays(1));
    oldVenue.setProgram(oldProgram);
    venueRepo.save(oldVenue);

    Program futureProgram0 = new Program();
    futureProgram0.setDescription("Zauberflöte"); // ö ä ü in DB?
    Artist otherArtist = new Artist("AnotherStar");
    futureProgram0.setArtists(new HashSet<>(Arrays.asList(otherArtist)));
    Venue futureVenue0 = new Venue();
    futureVenue0.setDate(localDateTime.plusMinutes(2));
    futureVenue0.setProgram(futureProgram0);
    futureVenue0 = venueRepo.save(futureVenue0);

    Program futureProgram1 = new Program();
    futureProgram1.setDescription("Moonwalk");
    futureProgram1.setArtists(new HashSet<>(Arrays.asList(artist)));
    Venue futureVenue1 = new Venue();
    futureVenue1.setDate(localDateTime.plusDays(1));
    futureVenue1.setProgram(futureProgram1);
    venueRepo.save(futureVenue1);

    VenueDto futureVenue0DTO = venueMapper.venueToVenueDto(futureVenue0, new MapperContext());
    assertEquals(
        Set.of(futureVenue0DTO),
        venueController.getAllVenuesByFilter(null, null, null, null, "AnotherStar"));
  }
}
