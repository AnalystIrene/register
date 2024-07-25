package com.registerapi.register.model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "registrations")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class RegistrationInfo {

    @Id
    private String id;

    private Integer studentId; // Reference to the student who is registered

    private String courseId; // If applicable, reference to the course the student is registered for

    private LocalDateTime registrationDate;
}
