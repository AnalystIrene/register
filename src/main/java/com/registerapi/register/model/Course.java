package com.registerapi.register.model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "courses")
@Data
@NoArgsConstructor
@AllArgsConstructor


public class Course {
    @Id
    private String id;

    private String name;

    private String description;

    private String instructor;

    private int credits;
}
