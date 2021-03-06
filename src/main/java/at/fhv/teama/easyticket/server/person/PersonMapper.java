package at.fhv.teama.easyticket.server.person;

import at.fhv.teama.easyticket.dto.PersonDto;
import at.fhv.teama.easyticket.server.mapping.MapperContext;
import at.fhv.teama.easyticket.server.address.AddressMapper;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

@Mapper(
    componentModel = "spring",
    nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
    uses = {AddressMapper.class})
public interface PersonMapper {

  PersonDto personToPersonDto(Person person, @Context MapperContext context);

  Person personDtoToPerson(PersonDto person, @Context MapperContext context);
}
