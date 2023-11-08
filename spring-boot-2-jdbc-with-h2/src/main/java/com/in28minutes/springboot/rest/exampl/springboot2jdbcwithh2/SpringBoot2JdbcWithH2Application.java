package com.in28minutes.springboot.rest.exampl.springboot2jdbcwithh2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



@SpringBootApplication
public class SpringBoot2JdbcWithH2Application implements CommandLineRunner {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    StudentJdbcRepository repository;

    public static void main(String[] args) {
        SpringApplication.run(SpringBoot2JdbcWithH2Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        logger.info("Student ID 10001 -> {}", repository.findById(10001L));

        logger.info("All users 1 -> {}", repository.findAll());

        logger.info("Inserting -> {}", repository.insert(new Student(10010L, "John", "A1234657")));

        logger.info("Update 10001 -> {}", repository.update(new Student(10001L, "Name-Updated", "New-Passport")));

        repository.deleteById(10002L);

        logger.info("All users 2 -> {}", repository.findAll());
        
        logger.info("Searching students with name 'NombreDelEstudiante' -> {}", repository.findByName("NombreDelEstudiante"));
    }
}
