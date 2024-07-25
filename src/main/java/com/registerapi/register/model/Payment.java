package com.registerapi.register.model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "payments")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Payment {
    @Id
    private String id;
    private Integer studentId; // Reference to the student making the payment
    private Double amount;     // Amount of the payment
    private String method;     // Payment method (e.g., mobile money, bank account)
    private String status;     // Payment status (e.g., pending, completed)
    private LocalDateTime paymentDate; // Date and time of the payment
    private String transactionId;

}
