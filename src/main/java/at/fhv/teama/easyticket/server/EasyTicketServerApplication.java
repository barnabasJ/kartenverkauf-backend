package at.fhv.teama.easyticket.server;

import at.fhv.teama.easyticket.server.messaging.MessagingController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import java.net.URISyntaxException;

@SpringBootApplication
@EnableConfigurationProperties
public class EasyTicketServerApplication {

  public static void main(String[] args) {
    SpringApplication.run(EasyTicketServerApplication.class, args);
    try {
      MessagingController.start_broker();
    } catch (URISyntaxException e) {
      e.printStackTrace();
    }
  }
}
