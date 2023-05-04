package com.theophilusgordon.marketsquareserver.service;

import com.theophilusgordon.marketsquareserver.entity.Payment;

import java.util.Optional;
import java.util.UUID;

public interface PaymentService {
    public Payment createPayment(Payment payment);
    public Optional<Payment> getPaymentById(UUID id);
    public Payment updatePayment(Payment payment);
    public void deletePayment(UUID id);
}
