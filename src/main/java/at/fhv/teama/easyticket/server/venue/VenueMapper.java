package at.fhv.teama.easyticket.server.venue;

import at.fhv.teama.easyticket.dto.VenueDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface VenueMapper {

  VenueDto venueToVenueDto(Venue venue);

  Venue venueDtoToVenue(VenueDto venue);
}
