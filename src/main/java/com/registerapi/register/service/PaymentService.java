package com.registerapi.register.service;

import com.registerapi.register.model.Payment;
import com.registerapi.register.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    public Payment processPayment(Payment payment) {
        // Here you would integrate with a payment gateway API
        // For now, we will just set the payment status to "completed" and save it

        payment.setPaymentDate(LocalDateTime.now());
        payment.setStatus("completed");

        return paymentRepository.save(payment);
    }

    public Payment getPaymentById(String id) {
        Optional<Payment> payment = paymentRepository.findById(id);
        return payment.orElse(null); // Return null if not found
    }

    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }
    public List<Payment> getPaymentsByStudentId(Integer studentId) {
        return paymentRepository.findByStudentId(studentId);
    }
    public Payment getPaymentByTransactionId(String transactionId) {
        return paymentRepository.findByTransactionId(transactionId);
    }


}
