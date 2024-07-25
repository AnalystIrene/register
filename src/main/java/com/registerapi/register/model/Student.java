package com.registerapi.register.model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection ="registers") //blueprints the class to be a db representation as a table
@Data //from lombok reduces the need to initialize setters and getters and constrctors
@NoArgsConstructor
@AllArgsConstructor

public class Student {
    //denates that regno is a primary key
    @Id
    private Integer regno;

    private String name;
    private String email;
    protected String age;

    private String address;
}
