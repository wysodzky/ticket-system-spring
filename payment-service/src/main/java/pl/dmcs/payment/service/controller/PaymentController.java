package pl.dmcs.payment.service.controller;

import com.stripe.exception.StripeException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.dmcs.payment.service.model.Order;
import pl.dmcs.payment.service.service.inf.PaymentService;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    private PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }


    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity makePayment(@RequestBody Order order) {
        try {
            paymentService.makePayment(order);
            return ResponseEntity.ok().build();
        } catch (StripeException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
