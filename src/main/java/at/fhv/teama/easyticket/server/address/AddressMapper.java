package at.fhv.teama.easyticket.server.address;

import at.fhv.teama.easyticket.dto.AddressDto;
import at.fhv.teama.easyticket.server.MapperContext;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.springframework.stereotype.Controller;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface AddressMapper {

  AddressDto addressToAddressDto(Address address, @Context MapperContext context);

  Address addressDtoToAddress(AddressDto address, @Context MapperContext context);
}
