package at.fhv.teama.kartenverkauf.backend.rmi;

import at.fhv.teama.kartenverkauf.dto.AddressDto;
import at.fhv.teama.kartenverkauf.rmi.EasyTicketService;

import java.rmi.RemoteException;

public class EasyTicketServiceImpl implements EasyTicketService {
    @Override
    public void saveAddress(AddressDto addressDto) throws RemoteException {
        System.out.println("Saving address");
    }
}
