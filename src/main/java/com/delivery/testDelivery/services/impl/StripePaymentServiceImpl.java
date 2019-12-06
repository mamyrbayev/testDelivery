package com.delivery.testDelivery.services.impl;

import com.delivery.testDelivery.services.StripePaymentService;
import com.stripe.Stripe;
import com.stripe.model.Charge;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class StripePaymentServiceImpl implements StripePaymentService {

    @Autowired
    StripePaymentServiceImpl() {
        Stripe.apiKey = "sk_test_Le5iKwlkY6TDn9PHkzqE2TEI00xRTRkthD";
    }

    @Override
    public Charge chargeNewCard(String token, double amount) throws Exception {
        Map<String, Object> chargeParams = new HashMap<String, Object>();
        chargeParams.put("amount", (int)(amount * 100));
        chargeParams.put("currency", "CAD");
        chargeParams.put("source", token);
        Charge charge = Charge.create(chargeParams);

        return charge;
    }
}
