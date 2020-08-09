package pl.dmcs.catalog.service.service.inf;


import pl.dmcs.catalog.service.exception.NoAvailableTicketFoundException;
import pl.dmcs.catalog.service.model.Ticket;
import pl.dmcs.catalog.service.model.dto.ReservationTicketDtoResult;
import pl.dmcs.catalog.service.model.dto.ReservationTicketDto;
import pl.dmcs.catalog.service.model.dto.TicketDto;

import java.util.List;

public interface TicketService {

    void save(Ticket ticket);

    void saveMultipleSameTickets(TicketDto ticketDto);

    boolean checkAvailability(String title);

    Ticket get(int id);

    List<Ticket> getAll();

    ReservationTicketDtoResult reserveTickets(ReservationTicketDto reservationTicketDto) throws NoAvailableTicketFoundException;
}
