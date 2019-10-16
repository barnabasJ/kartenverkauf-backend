package at.fhv.teama.kartenverkauf.backend.rmi;

import org.springframework.stereotype.Controller;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

@Controller
public class RmiTest extends UnicastRemoteObject implements IRmiTest {

    protected RmiTest() throws RemoteException {
    }

    public String test(String name) {
        System.out.println(name);
        return "success";
    }

}
