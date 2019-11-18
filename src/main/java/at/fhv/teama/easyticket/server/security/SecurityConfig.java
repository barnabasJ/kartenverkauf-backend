package at.fhv.teama.easyticket.server.security;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "easy-ticket.security")
public class SecurityConfig {
    public AuthType authtype;
    public LDAPConfig ldapConfig;

}
