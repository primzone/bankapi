package com.bank.api.service;

import com.bank.api.entity.Payment;
import com.bank.api.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {
    @Autowired
    PaymentRepository paymentRepository;


    public void save(Payment payment) {
        paymentRepository.save(payment);
    }
}
