package pl.dmcs.payment.service.service.inf;

import com.stripe.exception.StripeException;
import pl.dmcs.payment.service.model.Order;

public interface PaymentService {
    void makePayment(Order order) throws StripeException;
}
