package at.fhv.teama.kartenverkauf.backend;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
public class Program {
    @Id
    @GeneratedValue
    private Long id;
    private String genre;
    private String description;
}
