package org.example.hm9;

import org.example.hm9.model.Project;
import org.example.hm9.model.Timesheet;
import org.example.hm9.repository.ProjectRepository;
import org.example.hm9.repository.TimesheetRepository;
import org.example.aspect.Logging.LoggingAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Import;

import java.time.LocalDate;
import java.util.concurrent.ThreadLocalRandom;

@EnableDiscoveryClient
@SpringBootApplication
public class DZNineRestApplication {

	public static void main(String[] args) {

		ConfigurableApplicationContext ctx = SpringApplication.run(DZNineRestApplication.class, args);

		ProjectRepository projectRepo = ctx.getBean(ProjectRepository.class);

		for (int i = 1; i <= 5; i++) {
			Project project = new Project();
			project.setProjectName("Project #" + i);
			projectRepo.save(project);
		}

		TimesheetRepository timesheetRepo = ctx.getBean(TimesheetRepository.class);

		LocalDate createdAt = LocalDate.now();
		for (int i = 1; i <= 10; i++) {
			createdAt = createdAt.plusDays(1);

			Timesheet timesheet = new Timesheet();
			timesheet.setTimesheetProjectId(ThreadLocalRandom.current().nextLong(1, 6));
			timesheet.setCreatedAt(createdAt);
			timesheet.setMinutes(ThreadLocalRandom.current().nextInt(100, 1000));

			timesheetRepo.save(timesheet);
		}
	}
}
