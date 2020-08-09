package pl.dmcs.order.service.service.inf;

import pl.dmcs.order.service.model.Order;
import pl.dmcs.order.service.model.dto.OrderDto;
import pl.dmcs.order.service.model.dto.TicketDto;

public interface OrderService {
        void save(Order order);
        boolean checkAvailability(String title);
        Order getOrder(Integer id);
        void creatOrder(OrderDto orderDto);
}
