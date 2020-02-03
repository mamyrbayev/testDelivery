package com.delivery.testDelivery.controllers.rest;

import com.delivery.testDelivery.controllers.BaseController;
import com.delivery.testDelivery.models.requests.Payment;
import com.delivery.testDelivery.services.StripePaymentService;
import com.stripe.model.Charge;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/payment")
public class StripePaymentController extends BaseController {

    private final StripePaymentService stripePaymentService;

    @PostMapping("/charge")
    public Charge chargeCard(@RequestBody Payment payment) throws Exception {
//        String token = request.getHeader("token");
//        Double amount = Double.parseDouble(request.getHeader("amount"));
        return this.stripePaymentService.chargeNewCard(payment.getToken(), payment.getAmount(), payment.getDescription());
    }
}
