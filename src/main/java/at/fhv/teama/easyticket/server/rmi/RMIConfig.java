package at.fhv.teama.easyticket.server.rmi;

import at.fhv.teama.easyticket.rmi.EasyTicketService;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.rmi.RmiServiceExporter;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.rmi.registry.Registry;
import java.util.HashMap;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
@ConfigurationProperties(prefix = "server")
@Slf4j
public class RMIConfig {
  @Getter @Setter private int port;

  @Bean
  public PasswordEncoder passwordEncoder() {
    HashMap<String, PasswordEncoder> encoders = new HashMap<>();
    BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
    encoders.put("bcrypt", bCryptPasswordEncoder);

    DelegatingPasswordEncoder bcrypt = new DelegatingPasswordEncoder("bcrypt", encoders);
    bcrypt.setDefaultPasswordEncoderForMatches(bCryptPasswordEncoder);
    return bcrypt;
  }

  @Bean
  RmiServiceExporter exporter(EasyTicketService implementation) {
    Class<EasyTicketService> serviceInterface = EasyTicketService.class;
    log.info("String RMI Service on Port: " + port);
    RmiServiceExporter serviceExporter = new RmiServiceExporter();
    serviceExporter.setRegistryPort(port);
    serviceExporter.setServiceInterface(serviceInterface);
    serviceExporter.setService(implementation);
    serviceExporter.setServiceName(serviceInterface.getSimpleName());
    serviceExporter.setRegistryPort(Registry.REGISTRY_PORT);
    return serviceExporter;
  }
}
