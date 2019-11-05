package at.fhv.teama.easyticket.server.venue;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.jdbc.support.SQLErrorCodeSQLExceptionTranslator;

import java.time.LocalDateTime;
import java.util.Set;

public interface VenueRepository extends CrudRepository<Venue, Long> {
    /*
    Set<Venue> findAllByDateGreaterThanEqual(LocalDateTime date);

    Set<Venue> findAllByDateAndDateGreaterThanEqual(LocalDateTime date, LocalDateTime dateNow);

    Set<Venue> findAllByProgram_GenreLikeAndDateGreaterThanEqual(String genre, LocalDateTime date);

    Set<Venue> findAllByProgram_DescriptionLikeAndDateGreaterThanEqual(String description, LocalDateTime date);*/

    Set<Venue> findAllByDateGreaterThanEqualAndDateLessThanEqualAndProgram_GenreLikeAndProgram_DescriptionLike(LocalDateTime dateStart, LocalDateTime dateEnd, String genre, String description);
}
