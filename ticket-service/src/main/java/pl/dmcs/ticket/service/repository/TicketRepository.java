package pl.dmcs.ticket.service.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.dmcs.ticket.service.model.Ticket;

import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket,Integer> {
    List<Ticket> getAllByTitle(String title);
    List<Ticket> getAllByTitleAndReserved(String title,Boolean reserved);
}
