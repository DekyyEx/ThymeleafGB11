package org.example.springmvcthymeleaf.service;

import org.example.springmvcthymeleaf.entities.Timesheet;
import org.example.springmvcthymeleaf.repository.ProjectRepository;
import org.example.springmvcthymeleaf.repository.TimesheetRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;

@Service
public class TimesheetService {
    private final TimesheetRepository timesheetRepository;
    private final ProjectRepository projectRepository;

    public TimesheetService(TimesheetRepository repository, ProjectRepository projectRepository) {
        this.timesheetRepository = repository;
        this.projectRepository = projectRepository;
    }

    public Optional<Timesheet> findById(Long id) {
        return timesheetRepository.findById(id);
    }

    public List<Timesheet> findAll() {
        return findAll(null, null);
    }

    public List<Timesheet> findAll(LocalDate createdAtBefore, LocalDate createdAtAfter) {
        if (createdAtBefore == null && createdAtAfter == null) {
            return timesheetRepository.findAll();
        } else {
            return timesheetRepository.findByCreatedAtBeforeAndCreatedAtAfter(createdAtBefore, createdAtAfter);
        }
    }

    public Timesheet create(Timesheet timesheet) {
        if (Objects.isNull(timesheet.getProjectId())) {
            throw new IllegalArgumentException("projectId must not be null");
        }

        if (projectRepository.findById(timesheet.getProjectId()).isEmpty()) {
            throw new NoSuchElementException("'Project' с id = " + timesheet.getProjectId() + " не существует");
        }

        timesheet.setCreatedAt(LocalDate.now());
        return timesheetRepository.save(timesheet);
    }

    public void delete(Long id) {
        if (timesheetRepository.existsById(id)) {
            timesheetRepository.deleteById(id);
        } else {
            throw new NoSuchElementException("Timesheet with id=" + id + " does not exist");
        }
    }

    public List<Timesheet> findByEmployeeId(Long id) {
        return List.of();
    }
}
