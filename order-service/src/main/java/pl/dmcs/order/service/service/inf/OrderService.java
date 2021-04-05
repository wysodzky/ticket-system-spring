package pl.dmcs.order.service.service.inf;

import pl.dmcs.order.service.model.Order;
import pl.dmcs.order.service.model.dto.OrderDto;

public interface OrderService {
        void deleteOrder(Integer id);
        boolean checkAvailability(OrderDto orderDto);
        Order getOrder(Integer id);
        Integer createOrder(OrderDto orderDto);
        Boolean createOrderAndPay(OrderDto orderDto);
}
