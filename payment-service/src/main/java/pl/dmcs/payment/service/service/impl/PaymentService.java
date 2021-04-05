package pl.dmcs.payment.service.service.impl;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import org.springframework.stereotype.Service;
import pl.dmcs.payment.service.model.Order;
import pl.dmcs.payment.service.model.StripeCharge;

@Service
public class PaymentService implements pl.dmcs.payment.service.service.inf.PaymentService {

    private String API_KEY = "sk_test_51Ib9BBHR0fD774KgxXs8jeM1zEtHMf8CJEulWHiWtTWzjyJP3gbWnLrnFFMgt4L6hKM8ORs9XN9bp18DLABholgs00TLVYsjuo";

    @Override
    public void makePayment(Order order) throws StripeException {
        Stripe.apiKey = API_KEY;
        StripeCharge stripeCharge = new StripeCharge(1800L,"mail@test.com",order.getId(),order.getPersonIdentificationNumber());
        Charge charge = Charge.create(stripeCharge.getCharge());
        System.out.println(charge);
    }

}
