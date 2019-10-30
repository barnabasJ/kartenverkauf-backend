package at.fhv.teama.easyticket.server.person;

import at.fhv.teama.easyticket.dto.PersonDto;
import at.fhv.teama.easyticket.server.address.AddressMapper;
import at.fhv.teama.easyticket.server.venue.VenueMapper;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

@Mapper(
    componentModel = "spring",
    nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
    uses = {AddressMapper.class})
public interface PersonMapper {

  PersonDto personToPersonDto(Person person);

  Person personDtoToPerson(PersonDto person);
}
