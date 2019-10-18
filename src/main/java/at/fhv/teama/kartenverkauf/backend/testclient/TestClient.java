package at.fhv.teama.kartenverkauf.backend.testclient;

import at.fhv.teama.kartenverkauf.backend.rmi.IRmiTest;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Scanner;

public class TestClient {

    public static void main(String[] args) {
        Scanner stdIn = new Scanner(System.in);

        System.out.println("Enter first name");

        String input = stdIn.nextLine();

        try {
            IRmiTest rmitest = (IRmiTest) Naming.lookup("rmi://localhost/IRmiTest");

            System.out.println(rmitest.test(input));
        } catch (RemoteException | NotBoundException | MalformedURLException e) {
            System.out.println("Client error: " + e.getMessage());
            e.printStackTrace();
        }
    }

}
