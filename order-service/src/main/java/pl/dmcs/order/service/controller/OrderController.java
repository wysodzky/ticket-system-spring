package pl.dmcs.order.service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.dmcs.order.service.model.dto.OrderDto;
import pl.dmcs.order.service.service.inf.OrderService;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;


    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity makeOrder(@RequestBody OrderDto orderDto) {
        orderService.creatOrder(orderDto);
        return ResponseEntity.ok().build();
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity makeOrder(@PathVariable Integer id) {
        return ResponseEntity.ok(orderService.getOrder(id));
    }


}
