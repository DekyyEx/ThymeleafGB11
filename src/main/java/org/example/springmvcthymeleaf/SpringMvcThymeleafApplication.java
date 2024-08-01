package org.example.springmvcthymeleaf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class SpringMvcThymeleafApplication {
    public static void main(String[] args) {

        SpringApplication.run(SpringMvcThymeleafApplication.class, args);
    }
}

