package at.fhv.teama.easyticket.server;

import at.fhv.teama.easyticket.server.messaging.MessagingController;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import java.net.URISyntaxException;

public class ServletInitializer extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        try {
            MessagingController.start_broker();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return application.sources(EasyTicketServerApplication.class);
    }

}
