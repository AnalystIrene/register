package com.registerapi.register.model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
//import javax.validation.constraints.*;

@Document(collection = "registers")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student {

    @Id
    @NotNull(message = "Registration number cannot be null")
    private Integer regno;

    @NotBlank(message = "Name cannot be blank")
    @Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters")
    private String name;

    @NotBlank(message = "Email cannot be blank")
    @Email(message = "Please provide a valid Email address")
    private String email;

    @NotNull(message = "Age cannot be null")
    @Min(value = 18, message = "Student Must be 18 years and above")
    @Max(value = 60, message = "Age must be less than 150")
    private Integer age;

    @NotBlank(message = "Address cannot be blank")
    @Size(max = 100, message = "Address must be less than 100 characters")
    private String address;
}
