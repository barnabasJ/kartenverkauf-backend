package at.fhv.teama.easyticket.server.rmi;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "easy-ticket.rmi")
public class RmiConfig {
    private int port;
    private String hostname;
}
