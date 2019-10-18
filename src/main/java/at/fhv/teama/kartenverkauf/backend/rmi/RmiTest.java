package at.fhv.teama.kartenverkauf.backend.rmi;

public class RmiTest implements IRmiTest {

    public String test(String name) {
        System.out.println(name);
        return "success";
    }

}
