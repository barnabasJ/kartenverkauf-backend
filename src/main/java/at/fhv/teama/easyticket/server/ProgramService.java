package at.fhv.teama.easyticket.server;

import at.fhv.teama.kartenverkauf.dto.ProgramDto;
import org.springframework.stereotype.Service;

import javax.annotation.security.RolesAllowed;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

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

  @RolesAllowed({"User"})
  public List<Program> getAllPrograms() {
    return StreamSupport.stream(programRepo.findAll().spliterator(), false)
        .collect(Collectors.toList());
  }
}
