package at.fhv.teama.easyticket.server.user;

import at.fhv.teama.easyticket.server.ProgramService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class UserRunner implements CommandLineRunner {
  private PasswordEncoder passwordEncoder;
  private ProgramService programService;
  private UserRepository userRepo;

  public UserRunner(PasswordEncoder passwordEncoder, ProgramService programService, UserRepository userRepo) {
    this.passwordEncoder = passwordEncoder;
    this.programService = programService;
    this.userRepo = userRepo;
  }

  @Override
  public void run(String... args) throws Exception {
    log.info("UserRunner started");
    SecurityContext sc = SecurityContextHolder.getContext();
    log.info(sc.toString());
    User bob = new User("bob", passwordEncoder.encode("password"));
    userRepo.save(bob);
    Authentication request = new UsernamePasswordAuthenticationToken(bob.getUsername(), "password");
    request.setAuthenticated(false);
    sc.setAuthentication(request);
    programService.getAllPrograms();
    log.info("Accessed Secured Method");
  }
}
