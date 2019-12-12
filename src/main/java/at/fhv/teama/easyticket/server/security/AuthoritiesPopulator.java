package at.fhv.teama.easyticket.server.security;

import at.fhv.teama.easyticket.server.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.ldap.core.DirContextOperations;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.ldap.userdetails.LdapAuthoritiesPopulator;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class AuthoritiesPopulator implements LdapAuthoritiesPopulator {
  private final UserRepository userRepository;

  @Override
  public Collection<? extends GrantedAuthority> getGrantedAuthorities(
      DirContextOperations userData, String username) {
    return userRepository
        .findById(username)
        .map(
            u ->
                u.getRoles().stream()
                    .map(role -> new SimpleGrantedAuthority("ROLE_" + role))
                    .collect(Collectors.toList()))
        .orElse(Arrays.asList(new SimpleGrantedAuthority("ROLE_USER")));
  }
}
