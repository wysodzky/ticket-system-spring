package pl.dmcs.payment.service.model;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class Order implements Serializable {
    private Integer id;
    private List<Integer> ticketNumbers;
    private String personIdentificationNumber;
}
