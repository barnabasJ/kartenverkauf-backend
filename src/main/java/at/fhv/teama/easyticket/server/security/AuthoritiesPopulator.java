package at.fhv.teama.easyticket.server.security;

import org.springframework.ldap.core.DirContextOperations;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.ldap.userdetails.LdapAuthoritiesPopulator;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collection;

@Component
public class AuthoritiesPopulator implements LdapAuthoritiesPopulator {
  @Override
  public Collection<? extends GrantedAuthority> getGrantedAuthorities(
      DirContextOperations userData, String username) {
    return Arrays.asList(new SimpleGrantedAuthority("USER"));
  }
}
