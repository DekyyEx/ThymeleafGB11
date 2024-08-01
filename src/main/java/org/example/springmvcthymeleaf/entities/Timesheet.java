package org.example.springmvcthymeleaf.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;

@Setter
@Getter
@Data
@Entity
public class Timesheet {
    @jakarta.persistence.Id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @Column(name = "timesheet_id")
    private Long timesheetId;
    private Long id;



    @Column(name = "project_id", nullable = false)
    private Long projectId;
    private String projectName;

    @Column(name = "timesheet_project_id")
    private Long timesheetProjectId;


    @Column(nullable = false)
    private int minutes;

    @Column(name = "created_at", nullable = false)
    private LocalDate createdAt;
    @Setter
    @Getter
    private Long timesheetEmployeeId;

}
