package at.fhv.teama.easyticket.server.person;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class Artist {
    @Id
    private String name;
}
