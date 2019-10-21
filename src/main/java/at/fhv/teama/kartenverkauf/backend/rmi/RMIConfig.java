package at.fhv.teama.kartenverkauf.backend.rmi;

import at.fhv.teama.kartenverkauf.rmi.EasyTicketService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.rmi.RmiServiceExporter;

import java.rmi.RemoteException;
import java.rmi.registry.Registry;

@Configuration
public class RMIConfig {

    @Bean
    RmiServiceExporter  exporter(EasyTicketService implementation) {
        Class<EasyTicketService> serviceInterface = EasyTicketService.class;
        RmiServiceExporter serviceExporter = new RmiServiceExporter();
        serviceExporter.setServiceInterface(serviceInterface);
        serviceExporter.setService(implementation);
        serviceExporter.setServiceName(serviceInterface.getSimpleName());
        serviceExporter.setRegistryPort(Registry.REGISTRY_PORT);
        return serviceExporter;
    }
}
