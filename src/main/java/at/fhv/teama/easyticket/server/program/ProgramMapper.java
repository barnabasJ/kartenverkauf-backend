package at.fhv.teama.easyticket.server.program;

import at.fhv.teama.easyticket.dto.ProgramDto;
import at.fhv.teama.easyticket.server.person.ArtistMapper;
import at.fhv.teama.easyticket.server.person.PersonMapper;
import at.fhv.teama.easyticket.server.venue.VenueMapper;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

@Mapper(
        componentModel = "spring",
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        uses = {VenueMapper.class, PersonMapper.class, ArtistMapper.class})
public interface ProgramMapper {

    ProgramDto programToProgramDto(Program program);
    Program programDtoToProgram(ProgramDto program);
}
