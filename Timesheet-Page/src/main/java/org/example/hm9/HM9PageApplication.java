package org.example.HM11;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class HM9PageApplication {

    public static void main(String[] args) {

        SpringApplication.run(DZNinePageApplication.class, args);
    }
}