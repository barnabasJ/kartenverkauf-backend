package at.fhv.teama.kartenverkauf.backend.rmi;

import at.fhv.teama.kartenverkauf.backend.ProgramService;
import at.fhv.teama.kartenverkauf.dto.AddressDto;
import at.fhv.teama.kartenverkauf.dto.ProgramDto;
import at.fhv.teama.kartenverkauf.rmi.EasyTicketService;
import org.springframework.stereotype.Component;

import java.rmi.RemoteException;

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
        programService.saveProgram(programDto);

    }
}
