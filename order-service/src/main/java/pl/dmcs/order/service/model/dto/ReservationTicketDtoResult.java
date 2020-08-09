package pl.dmcs.order.service.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReservationTicketDtoResult implements Serializable {
    private List<Integer> ticketsNumbers;

}
