package com.registerapi.register.repository;

import com.registerapi.register.model.Payment;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentRepository extends MongoRepository<Payment, String> {

    List<Payment> findByStudentId(Integer studentId);


    Payment findByTransactionId(String transactionId);


}
