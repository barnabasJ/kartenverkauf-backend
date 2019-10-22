package at.fhv.teama.easyticket.server.user;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.core.context.SecurityContextHolder;

@Slf4j
public class UserRunner implements CommandLineRunner {
    private SecurityContextHolder securityContextHolder;

    public UserRunner(SecurityContextHolder securityContextHolder) {
        this.securityContextHolder = securityContextHolder;
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("UserRunner started");
    }
}
