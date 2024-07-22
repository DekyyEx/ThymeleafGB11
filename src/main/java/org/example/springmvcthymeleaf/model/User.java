package org.example.springmvcthymeleaf.model;


import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User {
    int id;
    String firstName;
    String lastName;
}
