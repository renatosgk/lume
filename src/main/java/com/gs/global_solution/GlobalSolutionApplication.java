package com.gs.global_solution;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.sql.*;

@SpringBootApplication
@EnableScheduling
public class GlobalSolutionApplication {

    public static void main(String[] args) {
        SpringApplication.run(GlobalSolutionApplication.class, args);
    }
}
