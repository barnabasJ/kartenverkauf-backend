package at.fhv.teama.easyticket.server.address;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
public class Address {
    @Id
    @GeneratedValue
    private Long id;
    private String line1;
    private String line2;
    private String line3;
    private String locality;
    private String region;
    private String postcode;
    private String country;
}
