package com.delivery.testDelivery.services;

import com.stripe.model.Charge;

public interface StripePaymentService {
    public Charge chargeNewCard(String token, double amount, String description) throws Exception;
}
