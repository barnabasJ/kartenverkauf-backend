package at.fhv.teama.easyticket.server;

import at.fhv.teama.kartenverkauf.dto.ProgramDto;
import org.springframework.stereotype.Service;

@Service
public class ProgramService {
    private ProgramRepository programRepo;
    private ProgramMapper programMapper;

    public ProgramService(ProgramRepository programRepo, ProgramMapper programMapper) {
        this.programRepo = programRepo;
        this.programMapper = programMapper;
    }

    public void saveProgram(ProgramDto programDto) {
        programRepo.save(programMapper.programDtoToProgram(programDto));

    }
}
