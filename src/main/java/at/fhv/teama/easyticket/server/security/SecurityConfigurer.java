package at.fhv.teama.easyticket.server.security;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashMap;

@Slf4j
@Configuration
@RequiredArgsConstructor
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class SecurityConfigurer {
    public final AuthoritiesPopulator authoritiesPopulator;
    public final SecurityConfig securityConfig;

    @Bean
    public PasswordEncoder passwordEncoder() {
        HashMap<String, PasswordEncoder> encoders = new HashMap<>();
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        encoders.put("bcrypt", bCryptPasswordEncoder);

        DelegatingPasswordEncoder bcrypt = new DelegatingPasswordEncoder("bcrypt", encoders);
        bcrypt.setDefaultPasswordEncoderForMatches(bCryptPasswordEncoder);
        return bcrypt;
    }

    @Autowired
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        log.info("Enable " + securityConfig.getAuthtype().toString() + " Authentication");
        switch (securityConfig.getAuthtype()) {
            case LDAP:
                setupLdapAuth(auth);
                break;
        }
    }

    private void setupLdapAuth(AuthenticationManagerBuilder auth) throws Exception {
        LDAPConfig ldapConfig = securityConfig.getLdapConfig();

        auth.ldapAuthentication()
                .userDnPatterns(ldapConfig.getUserDnPatterns())
                .groupSearchBase(ldapConfig.getGroupSearchBase())
                .userSearchBase(ldapConfig.getUserSearchBase())
                .userSearchFilter(ldapConfig.getUserSearchFilter())
                .ldapAuthoritiesPopulator(authoritiesPopulator)
                .contextSource()
                .managerDn(ldapConfig.getManagerDn())
                .managerPassword(ldapConfig.getManagerPassword())
                .url(ldapConfig.getUrl())
                .port(ldapConfig.getPort());
    }
}
