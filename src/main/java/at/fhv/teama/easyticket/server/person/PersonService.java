package at.fhv.teama.easyticket.server.person;

import at.fhv.teama.easyticket.dto.PersonDto;
import at.fhv.teama.easyticket.server.mapping.MapperContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class PersonService {

    private PersonRepo personRepo;
    private PersonMapper personMapper;

    public Set<PersonDto> getAllCustomer() {
        return StreamSupport.stream(personRepo.findAll().spliterator(), false)
                .map(p -> personMapper.personToPersonDto(p, new MapperContext()))
                .collect(Collectors.toSet());
    }
}
