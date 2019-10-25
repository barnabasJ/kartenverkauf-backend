package at.fhv.teama.easyticket.server.program;

import at.fhv.teama.easyticket.server.person.Artist;
import at.fhv.teama.easyticket.server.person.Person;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
public class Program {
    @Id
    @GeneratedValue
    private Long id;
    private String genre;
    private String description;
    @ManyToOne
    private Person organizer;
    @ManyToMany
    private Set<Artist> artists;
}
