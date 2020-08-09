package pl.dmcs.catalog.service.model.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class TicketDto implements Serializable {
    private String title;
    private String date;
    private String price;
    private Integer quantity;
}
