package com.example.lecspringdatajpa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(KeesunRegistrar.class)
public class LecSpringDataJpaApplication {

    public static void main(String[] args) {
        SpringApplication.run(LecSpringDataJpaApplication.class, args);
    }

}
