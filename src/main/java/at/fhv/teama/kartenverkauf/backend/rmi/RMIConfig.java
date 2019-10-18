package at.fhv.teama.kartenverkauf.backend.rmi;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.rmi.RmiServiceExporter;

import java.rmi.RemoteException;
import java.rmi.registry.Registry;

@Configuration
public class RMIConfig {

    @Bean
    IRmiTest rmiTest() throws RemoteException {
        return new RmiTest();
    }

    @Bean
    RmiServiceExporter  exporter(IRmiTest implementation) {
        Class<IRmiTest> serviceInterface = IRmiTest.class;
        RmiServiceExporter serviceExporter = new RmiServiceExporter();
        serviceExporter.setServiceInterface(serviceInterface);
        serviceExporter.setService(implementation);
        System.out.println(serviceInterface.getSimpleName());
        serviceExporter.setServiceName(serviceInterface.getSimpleName());
        serviceExporter.setRegistryPort(Registry.REGISTRY_PORT);
        return serviceExporter;
    }
}
