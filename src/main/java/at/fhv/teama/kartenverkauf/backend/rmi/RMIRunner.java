package at.fhv.teama.kartenverkauf.backend.rmi;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

@Component
public class RMIRunner implements CommandLineRunner {
    private RmiTest rmiTest;

    public RMIRunner(RmiTest rmiTest) {
        this.rmiTest = rmiTest;
    }

    @Override
    public void run(String... args) throws Exception {
        try {
            LocateRegistry.createRegistry(Registry.REGISTRY_PORT);

            Naming.rebind("test", rmiTest);

            System.out.println("Bound");
        } catch (RemoteException | MalformedURLException e) {
            System.out.println("RMI Runner error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
