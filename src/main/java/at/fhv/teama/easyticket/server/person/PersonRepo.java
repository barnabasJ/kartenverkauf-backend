package at.fhv.teama.easyticket.server.person;

import org.springframework.data.repository.CrudRepository;

public interface PersonRepo extends CrudRepository<Person, Long> {
}
