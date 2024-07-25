package org.example.springmvcthymeleaf.controller;

import org.example.springmvcthymeleaf.entities.Employee;
import org.example.springmvcthymeleaf.entities.Timesheet;
import org.example.springmvcthymeleaf.service.EmployeeService;
import org.example.springmvcthymeleaf.service.TimesheetService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    private final EmployeeService employeeService;
    private final TimesheetService timesheetService;

    public EmployeeController(EmployeeService employeeService, TimesheetService timesheetService) {
        this.employeeService = employeeService;
        this.timesheetService = timesheetService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> get(@PathVariable Long id) {
        return employeeService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Employee>> getAll() {
        return ResponseEntity.ok(employeeService.findAll());
    }

    @PostMapping
    public ResponseEntity<Employee> create(@RequestBody Employee employee) {
        return ResponseEntity.status(HttpStatus.CREATED).body(employeeService.create(employee));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        employeeService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/timesheets")
    public ResponseEntity<List<Timesheet>> getTimesheets(@PathVariable Long id) {
        Employee employee = employeeService.findById(id).orElseThrow(() -> new NoSuchElementException("Запись с id=" + id + " не существует"));
        return ResponseEntity.ok(timesheetService.findByEmployeeId(id));
    }
}

