package at.fhv.teama.easyticket.server.user;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;

import javax.annotation.security.RolesAllowed;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
public class UserController {

    @RolesAllowed("USER")
    public Set<String> getRoles() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            return ((UserDetails) principal)
                    .getAuthorities().stream().map(Object::toString).collect(Collectors.toSet());
        }
        return new HashSet<>();
    }
}
