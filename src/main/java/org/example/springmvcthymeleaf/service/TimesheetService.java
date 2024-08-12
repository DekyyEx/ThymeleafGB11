package org.example.springmvcthymeleaf.service;

import org.example.springmvcthymeleaf.Aspect.Recover;
import org.example.springmvcthymeleaf.Aspect.Timer;
import org.example.springmvcthymeleaf.repository.EmployeeRepository;
import org.example.springmvcthymeleaf.repository.ProjectRepository;
import org.example.springmvcthymeleaf.repository.TimesheetRepository;
import org.example.springmvcthymeleaf.entities.Timesheet;
import org.slf4j.event.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;

@Service
@Timer(level = Level.TRACE)
public class TimesheetService {

    private final TimesheetRepository timesheetRepository;
    private final ProjectRepository projectRepository;
    private final EmployeeRepository employeeRepository;


    public TimesheetService(TimesheetService timesheetService) {
        this(timesheetService.timesheetRepository, timesheetService.projectRepository, timesheetService.employeeRepository);
    }

    @Autowired
    public TimesheetService(TimesheetRepository timesheetRepository, ProjectRepository projectRepository, EmployeeRepository employeeRepository) {
        this.timesheetRepository = timesheetRepository;
        this.projectRepository = projectRepository;
        this.employeeRepository = employeeRepository;
    }


    private void findProjectOrThrow(Long id) {
        timesheetRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Project with id " + id + " does not exist"));
    }

    @Timer
    @Recover(noRecoverFor = {
            NoSuchElementException.class,
            IllegalStateException.class})
    public Optional<Timesheet> findById(Long id) {
        return Optional.ofNullable(timesheetRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Timesheet with id " + id + " does not exist")));
    }

    public List<Timesheet> findAll() {
        return timesheetRepository.findAll();
    }

    public List<Timesheet> findAll(LocalDate createdAtBefore, LocalDate createdAtAfter) {
        if (createdAtBefore != null && createdAtAfter != null) {
            return timesheetRepository.findByCreatedAtBetween(createdAtBefore, createdAtAfter);
        }
        return timesheetRepository.findAll();
    }

    public List<Timesheet> findAllByTimesheetProjectId(Long projectId) {
        findProjectOrThrow(projectId);
        return timesheetRepository.findAllByTimesheetProjectId(projectId);
    }

    public List<Timesheet> findAllByTimesheetEmployeeId(Long employeeId) {
        findProjectOrThrow(employeeId);
        return timesheetRepository.findAllByTimesheetEmployeeId(employeeId);
    }

    @Recover
    public Timesheet create(Timesheet timesheet) {
        if (Objects.isNull(timesheet.getTimesheetProjectId())) {
            throw new IllegalArgumentException("projectId must not be null");
        }

        if (projectRepository.findById(timesheet.getTimesheetProjectId()).isEmpty()) {
            throw new NoSuchElementException("Project with id " + timesheet.getTimesheetProjectId() + " does not exists");
        }

        timesheet.setCreatedAt(LocalDate.now());
        return timesheetRepository.save(timesheet);
    }

    public void delete(Long id) {
        findProjectOrThrow(id);
        timesheetRepository.deleteById(id);
    }

    public Optional<Timesheet> update(Long id, Timesheet updatedTimesheet) {
        return timesheetRepository.findById(id).map(existingTimesheet -> {
            existingTimesheet.setTimesheetProjectId(updatedTimesheet.getTimesheetProjectId());
            existingTimesheet.setTimesheetEmployeeId(updatedTimesheet.getTimesheetEmployeeId());
            existingTimesheet.setMinutes(updatedTimesheet.getMinutes());
            existingTimesheet.setCreatedAt(updatedTimesheet.getCreatedAt());
            return timesheetRepository.save(existingTimesheet);
        });
    }
}
