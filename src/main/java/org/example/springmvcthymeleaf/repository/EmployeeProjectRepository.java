package org.example.springmvcthymeleaf.repository;

import org.example.springmvcthymeleaf.entities.EmployeeProject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeProjectRepository extends JpaRepository<EmployeeProject, Long> {
}

