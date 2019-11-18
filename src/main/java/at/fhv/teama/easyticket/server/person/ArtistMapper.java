package at.fhv.teama.easyticket.server.person;

import at.fhv.teama.easyticket.dto.ArtistDto;
import at.fhv.teama.easyticket.server.mapping.MapperContext;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface ArtistMapper {

  ArtistDto artistToArtistDto(Artist artist, @Context MapperContext context);

  Artist artistDtoToArtist(ArtistDto artist, @Context MapperContext context);
}
