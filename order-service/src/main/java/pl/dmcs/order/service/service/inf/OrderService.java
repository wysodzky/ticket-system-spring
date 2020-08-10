package pl.dmcs.order.service.service.inf;

import pl.dmcs.order.service.model.Order;
import pl.dmcs.order.service.model.dto.OrderDto;

public interface OrderService {
        void save(Order order);
        void deleteOrder(Integer id);
        boolean checkAvailability(OrderDto orderDto);
        Order getOrder(Integer id);
        void creatOrder(OrderDto orderDto);
}
