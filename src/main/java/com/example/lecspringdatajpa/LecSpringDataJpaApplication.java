package com.example.lecspringdatajpa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.QueryLookupStrategy;

@SpringBootApplication
public class LecSpringDataJpaApplication {

    public static void main(String[] args) {
        SpringApplication.run(LecSpringDataJpaApplication.class, args);
    }

}
