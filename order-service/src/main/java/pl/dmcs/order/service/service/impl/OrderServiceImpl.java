package pl.dmcs.order.service.service.impl;

import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pl.dmcs.order.service.model.Order;
import pl.dmcs.order.service.model.dto.OrderDto;
import pl.dmcs.order.service.model.dto.ReservationTicketDto;
import pl.dmcs.order.service.model.dto.ReservationTicketDtoResult;
import pl.dmcs.order.service.model.dto.TicketDto;
import pl.dmcs.order.service.repository.OrderRepository;
import pl.dmcs.order.service.service.inf.OrderService;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private static final String TICKET_SERVICE_URL = "http://localhost:8080/ticket-service/tickets";

    private final OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }


    @Override
    public void save(Order order) {
        orderRepository.save(order);
    }

    @Override
    public void deleteOrder(Integer id) {
        orderRepository.deleteById(id);
    }

    @Override
    public boolean checkAvailability(OrderDto orderDto) {
        RestTemplate restTemplate = new RestTemplate();
            Boolean available = restTemplate.getForObject(TICKET_SERVICE_URL +"/availability/"+orderDto.getTicket().getTitle(),Boolean.class);
            if (!available) {
                return false;
            }
        return true;
    }

    @Override
    public Order getOrder(Integer id) {
        return orderRepository.getOne(id);
    }

    @Override
    public void creatOrder(OrderDto orderDto) {
        RestTemplate restTemplate = new RestTemplate();

        List<Integer> tickerNumbers = new ArrayList<>();

        Order order = new Order();
        TicketDto ticketDto = orderDto.getTicket();

        ReservationTicketDto reservationTicketDto = new ReservationTicketDto();
        reservationTicketDto.setQuantity(ticketDto.getQuantity());
        reservationTicketDto.setTitle(ticketDto.getTitle());

        HttpEntity<ReservationTicketDto> request = new HttpEntity<>(reservationTicketDto);

        ReservationTicketDtoResult reservationTicketDtoResult = restTemplate.
                postForObject(TICKET_SERVICE_URL + "/check", request, ReservationTicketDtoResult.class);
        if (reservationTicketDtoResult != null && reservationTicketDtoResult.getTicketsNumbers() != null
                && reservationTicketDtoResult.getTicketsNumbers().size() > 0) {
            tickerNumbers.addAll(reservationTicketDtoResult.getTicketsNumbers());
        }


        order.setTicketNumbers(tickerNumbers);
        order.setPersonIdentificationNumber(orderDto.getPersonIdentificationNumber());
        orderRepository.save(order);
    }
}
