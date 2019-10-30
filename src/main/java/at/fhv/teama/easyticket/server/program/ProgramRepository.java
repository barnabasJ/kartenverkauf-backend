package at.fhv.teama.easyticket.server.program;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

public interface ProgramRepository extends CrudRepository<Program, Long> {

    @Query(value = "SELECT DISTINCT genre FROM program", nativeQuery = true)
    Set<String> getAllGenres();
}
