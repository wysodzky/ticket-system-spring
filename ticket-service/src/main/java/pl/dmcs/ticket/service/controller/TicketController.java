package pl.dmcs.ticket.service.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.dmcs.ticket.service.exception.NoAvailableTicketFoundException;
import pl.dmcs.ticket.service.model.Ticket;
import pl.dmcs.ticket.service.model.dto.ReservationTicketDto;
import pl.dmcs.ticket.service.model.dto.ReservationTicketDtoResult;
import pl.dmcs.ticket.service.model.dto.TicketDto;
import pl.dmcs.ticket.service.service.inf.TicketService;

@RestController
@RequestMapping("/tickets")
public class TicketController {

    private final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity saveTicket(@RequestBody Ticket ticket)  {
        ticketService.save(ticket);
        return ResponseEntity.ok().build();
    }


    @RequestMapping(value = "/multiple", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity saveMultipleTickets(@RequestBody TicketDto ticketDto)  {
        ticketService.saveMultipleSameTickets(ticketDto);
        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity getTicket(@PathVariable("id") int ticketId)  {
        return ResponseEntity.ok(ticketService.get(ticketId));
    }

    @RequestMapping(value = "/availability/{title}",method = RequestMethod.GET)
    @ResponseBody
    public Boolean checkAvailability(@PathVariable("title") String title) {
        return ticketService.checkAvailability(title);
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity getAllTickets(){
        return ResponseEntity.ok(ticketService.getAll());
    }

    @RequestMapping(value = "/check", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity checkAndReserveTickets(@RequestBody ReservationTicketDto reservationTicketDto) {
        ReservationTicketDtoResult reservationTicketDtoResult;
        try {
            reservationTicketDtoResult = ticketService.reserveTickets(reservationTicketDto);
        } catch (NoAvailableTicketFoundException e) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(reservationTicketDtoResult);
    }
}
