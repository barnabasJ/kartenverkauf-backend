package at.fhv.teama.easyticket.server.rmi;

import at.fhv.teama.easyticket.server.ProgramService;
import at.fhv.teama.kartenverkauf.dto.AddressDto;
import at.fhv.teama.kartenverkauf.dto.ProgramDto;
import at.fhv.teama.kartenverkauf.rmi.EasyTicketService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.rmi.RemoteException;

@Slf4j
@Component
public class EasyTicketServiceImpl implements EasyTicketService {
    private ProgramService programService;

    public EasyTicketServiceImpl(ProgramService programService) {
        this.programService = programService;
    }

    @Override
    public void saveAddress(AddressDto addressDto) throws RemoteException {
        System.out.println("Saving address");
    }

    @Override
    public void saveProgram(ProgramDto programDto) {
        // call secured method
        System.out.println(SecurityContextHolder.getContext().getAuthentication());
        log.info("remote method was called");
        programService.getAllPrograms();
        programService.saveProgram(programDto);
    }
}
