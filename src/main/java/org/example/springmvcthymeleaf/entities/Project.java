package org.example.springmvcthymeleaf.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;


import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "project")
public class Project {
    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;
    @Column(name = "project_name")
    private String projectName;

}
