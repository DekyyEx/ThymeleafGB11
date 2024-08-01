package org.example.springmvcthymeleaf.repository;

import org.example.springmvcthymeleaf.entities.Employee;
import org.example.springmvcthymeleaf.entities.Project;
import org.example.springmvcthymeleaf.entities.Timesheet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;


@EnableJpaRepositories
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    Optional<Employee> findByEmployeeName(String employeeName);

    @Query("SELECT p FROM EmployeeProject ep JOIN ep.project p WHERE ep.employee.id = :id")
    List<Project> findEmployeeProjects(@Param("id") Long id);

    @Query("SELECT tm FROM Timesheet tm WHERE tm.timesheetEmployeeId = :id")
    List<Timesheet> findEmployeeTimesheets(@Param("id") Long id);
}

