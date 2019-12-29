package at.fhv.teama.easyticket.server.person;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class PersonService {
  private final PersonRepo personRepo;

  public Set<Person> getAllCustomer() {
    return StreamSupport.stream(personRepo.findAll().spliterator(), false)
            .collect(Collectors.toSet());
  }
}
