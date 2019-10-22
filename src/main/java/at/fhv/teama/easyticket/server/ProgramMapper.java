package at.fhv.teama.easyticket.server;

import at.fhv.teama.kartenverkauf.dto.ProgramDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProgramMapper {

    ProgramDto programToProgramDto(Program program);
    Program programDtoToProgram(ProgramDto program);
}