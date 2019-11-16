package at.fhv.teama.easyticket.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import java.security.*;

@SpringBootApplication
@EnableConfigurationProperties
public class EasyTicketServerApplication {

  public static void main(String[] args) {
    Policy.setPolicy(
        new Policy() {
          @Override
          public PermissionCollection getPermissions(CodeSource codesource) {
            Permissions p = new Permissions();
            p.add(new AllPermission());
            return p;
          }
        });
    SpringApplication.run(EasyTicketServerApplication.class, args);
  }
}
