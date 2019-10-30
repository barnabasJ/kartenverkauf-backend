package at.fhv.teama.easyticket.server.person;

import at.fhv.teama.easyticket.dto.ArtistDto;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface ArtistMapper {

  ArtistDto artistToArtistDto(Artist artist);

  Artist artistDtoToArtist(ArtistDto artist);
}
