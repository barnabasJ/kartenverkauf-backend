package at.fhv.teama.easyticket.server.venue;

import at.fhv.teama.easyticket.dto.VenueDto;
import at.fhv.teama.easyticket.server.address.AddressMapper;
import at.fhv.teama.easyticket.server.person.PersonMapper;
import at.fhv.teama.easyticket.server.program.ProgramMapper;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

@Mapper(
    componentModel = "spring",
    nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
    uses = {ProgramMapper.class, PersonMapper.class, AddressMapper.class})
public interface VenueMapper {

  VenueDto venueToVenueDto(Venue venue);

  Venue venueDtoToVenue(VenueDto venue);
}
