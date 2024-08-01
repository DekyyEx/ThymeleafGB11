package org.example.springmvcthymeleaf.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;


import java.util.HashSet;
import java.util.Set;


@Data
@Entity
@Table(name = "employee")
public class Employee {

    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    private String firstName;
    private String lastName;
    private int age;
    private double salary;

    @Column(name = "employee_name")
    private String employeeName;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "employee_project",
            joinColumns = @JoinColumn(name = "employee_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "project_id", referencedColumnName = "id"))
    private Set<Project> projects = new HashSet<>();

    public Employee() {}

    public Employee(String firstName, String lastName, int age, double salary) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.salary = salary;
    }
}