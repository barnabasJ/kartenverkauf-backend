package at.fhv.teama.easyticket.server.program;

import at.fhv.teama.easyticket.dto.ProgramDto;
import at.fhv.teama.easyticket.server.MapperContext;
import org.springframework.stereotype.Service;

import javax.annotation.security.RolesAllowed;
import javax.persistence.EntityManager;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ProgramService {
  private ProgramRepository programRepo;
  private ProgramMapper programMapper;
  private EntityManager em;

  public ProgramService(ProgramRepository programRepo, ProgramMapper programMapper, EntityManager em) {
    this.programRepo = programRepo;
    this.programMapper = programMapper;
    this.em = em;
  }

  public void saveProgram(ProgramDto programDto) {
    programRepo.save(programMapper.programDtoToProgram(programDto, new MapperContext(em)));
  }

  @RolesAllowed({"User"})
  public List<Program> getAllPrograms() {
    return StreamSupport.stream(programRepo.findAll().spliterator(), false)
        .collect(Collectors.toList());
  }
}
