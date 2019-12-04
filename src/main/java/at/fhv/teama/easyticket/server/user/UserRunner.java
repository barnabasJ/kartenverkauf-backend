package at.fhv.teama.easyticket.server.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
public class UserRunner implements CommandLineRunner {
  private final PasswordEncoder passwordEncoder;
  private final UserRepository userRepository;

  @Override
  public void run(String... args) throws Exception {
    EasyTicketUser bob = new EasyTicketUser();
    bob.setUsername("bob");
    bob.setPassword(passwordEncoder.encode("password"));
    bob.setRoles(Arrays.asList(Roles.USER).stream().collect(Collectors.toSet()));
    EasyTicketUser alice = new EasyTicketUser();
    alice.setUsername("alice");
    alice.setPassword(passwordEncoder.encode("password"));
    alice.setRoles(Arrays.asList(Roles.USER, Roles.PUBLISHER).stream().collect(Collectors.toSet()));

    log.info(Roles.USER.toString());

    userRepository.save(bob);
    userRepository.save(alice);
  }
}
