package pl.dmcs.order.service.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.dmcs.order.service.model.dto.OrderDto;
import pl.dmcs.order.service.service.inf.OrderService;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }


    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity makeOrder(@RequestBody OrderDto orderDto) {
        if (orderService.checkAvailability(orderDto)) {
            orderService.createOrder(orderDto);
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.badRequest().build();
    }

    @RequestMapping(value = "/payOrder", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity makeOrderAndPay(@RequestBody OrderDto orderDto) {
        if (orderService.checkAvailability(orderDto)) {
            if (orderService.createOrderAndPay(orderDto)) {
                return ResponseEntity.ok().build();
            } else {
                return ResponseEntity.badRequest().build();
            }
        }
        return ResponseEntity.badRequest().build();
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity getOrder(@PathVariable Integer id) {
        return ResponseEntity.ok(orderService.getOrder(id));
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity deleteOrder(@PathVariable Integer id) {
        orderService.deleteOrder(id);
        return ResponseEntity.ok().build();
    }


}
