package pl.dmcs.catalog.service.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.dmcs.catalog.service.exception.NoAvailableTicketFoundException;
import pl.dmcs.catalog.service.model.Ticket;
import pl.dmcs.catalog.service.model.dto.ReservationTicketDtoResult;
import pl.dmcs.catalog.service.model.dto.ReservationTicketDto;
import pl.dmcs.catalog.service.model.dto.TicketDto;
import pl.dmcs.catalog.service.repository.TicketRepository;
import pl.dmcs.catalog.service.service.inf.TicketService;

import java.util.ArrayList;
import java.util.List;

@Service
public class TicketServiceImpl implements TicketService {

    @Autowired
    private TicketRepository ticketRepository;


    @Override
    public void save(Ticket ticket) {
        ticketRepository.save(ticket);
    }

    @Override
    public void saveMultipleSameTickets(TicketDto ticketDto) {
        Integer quantity = ticketDto.getQuantity();

        for(int i=0 ;i< quantity; i++){
            Ticket ticket = new Ticket();
            ticket.setReserved(false);
            ticket.setDate(ticketDto.getDate());
            ticket.setPrice(ticketDto.getPrice());
            ticket.setTitle(ticketDto.getTitle());
            ticketRepository.save(ticket);
        }
    }

    @Override
    public boolean checkAvailability(String title) {
        List<Ticket> tickets = ticketRepository.getAllByTitle(title);
        return tickets.size() > 0;
    }


    @Override
    public Ticket get(int id) {
        return ticketRepository.getOne(id);
    }

    @Override
    public List<Ticket> getAll() {
        return ticketRepository.findAll();
    }

    @Override
    public ReservationTicketDtoResult reserveTickets(ReservationTicketDto reservationTicketDto) throws NoAvailableTicketFoundException {
        List<Ticket> tickets = ticketRepository.getAllByTitle(reservationTicketDto.getTitle());
        if (!(tickets.size() > 0)) {
            throw new NoAvailableTicketFoundException(reservationTicketDto.getTitle());
        }

        if (!(tickets.size()>= reservationTicketDto.getQuantity())){
            throw new NoAvailableTicketFoundException(reservationTicketDto.getTitle());
        }

        List<Ticket> subList = tickets.subList(tickets.size() - reservationTicketDto.getQuantity(),tickets.size());
        List<Integer> reservedTickets = new ArrayList<>();

        for (Ticket ticket : subList) {
            ticket.setReserved(true);
            ticketRepository.save(ticket);
            reservedTickets.add(ticket.getId());
        }


        return new ReservationTicketDtoResult(reservedTickets);
    }
}
