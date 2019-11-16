package at.fhv.teama.easyticket.server.rmi;

import at.fhv.teama.easyticket.rmi.EasyTicketService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.rmi.RmiServiceExporter;

@Slf4j
@Configuration
public class RmiConfigurer {
  private final int port;
  private final String hostname;

  public RmiConfigurer(RmiConfig rmiConfig) {
    port = rmiConfig.getPort();
    hostname = rmiConfig.getHostname();
  }

  @Bean
  RmiServiceExporter exporter(EasyTicketService implementation) {
    log.info(port + " " + hostname);
    Class<EasyTicketService> serviceInterface = EasyTicketService.class;
    RmiServiceExporter serviceExporter = new RmiServiceExporter();

    if (port >= 0 && port <= 65535) {
      serviceExporter.setRegistryPort(port);
      log.info("Exposing RMI Service on port: " + port);
    }

    if (hostname != null && !hostname.isBlank()) {
      System.setProperty("java.rmi.server.hostname", hostname);
      log.info("Exposing RMI hostname to: " + hostname);
    }

    serviceExporter.setServiceInterface(serviceInterface);
    serviceExporter.setService(implementation);
    serviceExporter.setServiceName(serviceInterface.getSimpleName());
    return serviceExporter;
  }
}
