package at.fhv.teama.easyticket.server.testclient;

import at.fhv.teama.kartenverkauf.dto.AddressDto;
import at.fhv.teama.kartenverkauf.rmi.EasyTicketService;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class TestClient {

    public static void main(String[] args) {
        try {
            EasyTicketService rmitest = (EasyTicketService) Naming.lookup("rmi://localhost/IRmiTest");
            rmitest.saveAddress(new AddressDto());
            System.out.println("Saved Address");
        } catch (RemoteException | NotBoundException | MalformedURLException e) {
            System.out.println("Client error: " + e.getMessage());
            e.printStackTrace();
        }
    }

}
