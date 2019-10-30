package at.fhv.teama.easyticket.server.address;

import at.fhv.teama.easyticket.dto.AddressDto;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface AddressMapper {

  AddressDto addressToAddressDto(Address address);

  Address addressDtoToAddress(AddressDto address);
}
