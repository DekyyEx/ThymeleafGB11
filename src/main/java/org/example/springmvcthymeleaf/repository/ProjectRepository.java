package org.example.springmvcthymeleaf.repository;

import org.example.springmvcthymeleaf.entities.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
}
