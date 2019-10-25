package at.fhv.teama.easyticket.server.person;

import at.fhv.teama.easyticket.server.address.Address;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Data
@Entity
public class Person {
    @Id
    @GeneratedValue
    private Long id;
    private String firstname;
    private String lastname;
    private String email;
    private String phone;
    @ManyToOne
    private Address address;
}
