package at.fhv.teama.kartenverkauf.backend.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IRmiTest extends Remote {
    String test(String name) throws RemoteException;
}
