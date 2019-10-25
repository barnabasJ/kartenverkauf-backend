package at.fhv.teama.easyticket.server.venue.ticket;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
public class Category {
  @Id @GeneratedValue private Long id;
  private Integer price;
}
