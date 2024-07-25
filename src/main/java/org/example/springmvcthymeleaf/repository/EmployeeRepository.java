package org.example.springmvcthymeleaf.repository;

import org.example.springmvcthymeleaf.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@EnableJpaRepositories
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}

