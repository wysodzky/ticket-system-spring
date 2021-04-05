package pl.dmcs.order.service.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id;

    @ElementCollection
    private List<Integer> ticketNumbers;

    @Column
    private String personIdentificationNumber;

    @Column
    private boolean paid;

}
