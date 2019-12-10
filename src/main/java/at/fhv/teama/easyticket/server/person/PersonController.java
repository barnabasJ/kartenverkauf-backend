package at.fhv.teama.easyticket.server.person;

import at.fhv.teama.easyticket.dto.PersonDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

import java.util.Set;

@Controller
@RequiredArgsConstructor
public class PersonController {

    private final PersonService personService;
    public Set<PersonDto> getAllCustomer(){
        return personService.getAllCustomer();
    }
}
