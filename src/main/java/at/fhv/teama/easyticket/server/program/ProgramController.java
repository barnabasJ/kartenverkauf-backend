package at.fhv.teama.easyticket.server.program;

import at.fhv.teama.easyticket.dto.ProgramDto;
import at.fhv.teama.easyticket.server.MapperContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

import javax.persistence.EntityManager;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class ProgramController {
    private final ProgramService programService;
    private final ProgramMapper programMapper;
    private final EntityManager em;

    public ProgramDto saveProgram(ProgramDto programDto) {
        return programMapper.programToProgramDto(programService.saveProgram(programMapper.programDtoToProgram(programDto, new MapperContext(em))), new MapperContext());
    }

    public Set<ProgramDto> getAllPrograms() {
        return programService.getAllPrograms().stream().map(p -> programMapper.programToProgramDto(p, new MapperContext())).collect(Collectors.toSet());

    }
}
