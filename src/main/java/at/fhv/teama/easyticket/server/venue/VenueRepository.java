package at.fhv.teama.easyticket.server.venue;

import org.springframework.data.repository.CrudRepository;

import java.time.LocalDateTime;
import java.util.Set;

public interface VenueRepository extends CrudRepository<Venue, Long> {

  Set<Venue>
      findAllByDateGreaterThanEqualAndDateLessThanEqualAndProgram_GenreLikeAndProgram_DescriptionLikeAndProgram_Artists_NameLike(
          LocalDateTime dateStart,
          LocalDateTime dateEnd,
          String genre,
          String description,
          String artist);
}
