package pl.dmcs.ticket.service.service.inf;


import pl.dmcs.ticket.service.exception.NoAvailableTicketFoundException;
import pl.dmcs.ticket.service.model.Ticket;
import pl.dmcs.ticket.service.model.dto.ReservationTicketDtoResult;
import pl.dmcs.ticket.service.model.dto.ReservationTicketDto;
import pl.dmcs.ticket.service.model.dto.TicketDto;

import java.util.List;

public interface TicketService {

    void save(Ticket ticket);

    void saveMultipleSameTickets(TicketDto ticketDto);

    boolean checkAvailability(String title);

    Ticket get(int id);

    List<Ticket> getAll();

    ReservationTicketDtoResult reserveTickets(ReservationTicketDto reservationTicketDto) throws NoAvailableTicketFoundException;
}
