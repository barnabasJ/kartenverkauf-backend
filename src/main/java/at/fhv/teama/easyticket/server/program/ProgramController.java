package at.fhv.teama.easyticket.server.program;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor

public class ProgramController {

    private final ProgramService programService;
    private final ProgramMapper programMapper;
}
