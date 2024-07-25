package org.example.springmvcthymeleaf;

import org.example.springmvcthymeleaf.entities.Employee;
import org.example.springmvcthymeleaf.entities.Project;
import org.example.springmvcthymeleaf.entities.Timesheet;
import org.example.springmvcthymeleaf.repository.EmployeeRepository;
import org.example.springmvcthymeleaf.repository.ProjectRepository;
import org.example.springmvcthymeleaf.repository.TimesheetRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.concurrent.ThreadLocalRandom;

@SpringBootApplication
@ConfigurationPropertiesScan
public class SpringMvcThymeleafApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringMvcThymeleafApplication.class, args);
    }

    @Bean
    public CommandLineRunner proj(ProjectRepository projectRepo, TimesheetRepository timesheetRepo) {
        return (args) -> {
            for (int i = 1; i <= 5; i++) {
                Project project = new Project();
                projectRepo.save(project);
            }

            LocalDate createdAt = LocalDate.now();
            for (int i = 1; i <= 10; i++) {
                createdAt = createdAt.plusDays(1);
                Timesheet timesheet;
                timesheet = new Timesheet();
                timesheet.setProjectId(ThreadLocalRandom.current().nextLong(1, 6));
                timesheet.setCreatedAt(createdAt);
                timesheet.setMinutes(ThreadLocalRandom.current().nextInt(100, 1000));
                timesheetRepo.save(timesheet);
            }
        };
    }

    @Bean
    CommandLineRunner initDatabase(EmployeeRepository employeeRepository) {
        return args -> {
            employeeRepository.save(new Employee("Иван", "Иванов", 32, 300000));
            employeeRepository.save(new Employee("Александр", "Иванов", 25, 31000));
            employeeRepository.save(new Employee("Макар", "Агафонов", 40, 12000));
            employeeRepository.save(new Employee("Маргарита", "Фомина", 35, 50100));
            employeeRepository.save(new Employee("София", "Фадеева", 28, 72000));
            employeeRepository.save(new Employee("Илья", "Андреев", 32, 80300));
            employeeRepository.save(new Employee("Вероника", "Павловна", 45, 102000));
        };
    }

}

