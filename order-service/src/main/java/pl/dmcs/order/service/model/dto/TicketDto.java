package pl.dmcs.order.service.model.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class TicketDto implements Serializable {
    private String title;
    private Integer quantity;
}
