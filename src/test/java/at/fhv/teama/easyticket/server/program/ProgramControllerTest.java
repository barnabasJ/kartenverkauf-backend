package at.fhv.teama.easyticket.server.program;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class ProgramControllerTest {
  @Autowired private ProgramRepository programRepository;
  @Autowired private ProgramController progamController;
  @Autowired private ProgramMapper programMapper;

  @Test
  public void test() {}
}
