package at.fhv.teama.easyticket.server.person;

import at.fhv.teama.easyticket.dto.PersonDto;
import at.fhv.teama.easyticket.server.mapping.MapperContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class PersonController {
  private final PersonMapper personMapper;
  private final PersonService personService;

  public Set<PersonDto> getAllCustomer() {
    return personService.getAllCustomer().stream()
            .map(p -> personMapper.personToPersonDto(p, new MapperContext()))
            .collect(Collectors.toSet());
  }
}
