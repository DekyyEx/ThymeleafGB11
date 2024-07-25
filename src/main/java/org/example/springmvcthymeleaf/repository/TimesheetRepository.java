package org.example.springmvcthymeleaf.repository;

import org.example.springmvcthymeleaf.entities.Employee;
import org.example.springmvcthymeleaf.entities.Timesheet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.time.LocalDate;
import java.util.List;

@EnableJpaRepositories
public interface TimesheetRepository extends JpaRepository<Timesheet, Long> {

    List<Timesheet> findByProjectId(Long projectId);

    List<Timesheet> findByCreatedAtBeforeAndCreatedAtAfter(LocalDate createdAtBefore, LocalDate createdAtAfter);

    List<Timesheet> findByEmployee(Employee employee);
}
