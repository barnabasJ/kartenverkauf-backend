package at.fhv.teama.easyticket.server.program;

import at.fhv.teama.easyticket.dto.ProgramDto;
import at.fhv.teama.easyticket.server.MapperContext;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.Set;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class ProgramControllerTest {
  @Autowired private ProgramRepository programRepository;
  @Autowired private ProgramController programController;
  @Autowired private ProgramMapper programMapper;

  @Test
  public void getAllPrograms() {

    Program program1 = new Program();
    program1.setDescription("Test1");
    program1 = programRepository.save(program1);

    Program program2 = new Program();
    program2.setDescription("Test2");
    program2 = programRepository.save(program2);

    Program program3 = new Program();
    program3.setDescription("Test3");
    program3 = programRepository.save(program3);

    ProgramDto program1Dto = programMapper.programToProgramDto(program1, new MapperContext());
    ProgramDto program2Dto = programMapper.programToProgramDto(program2, new MapperContext());
    ProgramDto program3Dto = programMapper.programToProgramDto(program3, new MapperContext());
    assertEquals(Set.of(program1Dto, program2Dto, program3Dto), programController.getAllPrograms());

  }

  @Test
  public void save()
  {
    Program program1 = new Program();
    program1.setDescription("Test1");
    ProgramDto program1Dto = programMapper.programToProgramDto(program1,new MapperContext());
    program1Dto = programController.saveProgram(program1Dto);

    Program program2 = new Program();
    program2.setDescription("Test2");
    ProgramDto program2Dto = programMapper.programToProgramDto(program2,new MapperContext());
    program2Dto = programController.saveProgram(program2Dto);

    Program program3 = new Program();
    program3.setDescription("Test3");
    ProgramDto program3Dto = programMapper.programToProgramDto(program3,new MapperContext());
    program3Dto = programController.saveProgram(program3Dto);

    assertEquals(Set.of(program1Dto,program2Dto,program3Dto),programController.getAllPrograms());
  }
}
