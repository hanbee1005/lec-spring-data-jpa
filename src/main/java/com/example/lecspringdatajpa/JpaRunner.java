package com.example.lecspringdatajpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class JpaRunner implements ApplicationRunner {

    @Autowired
    PostRepository postRepository;

    @Autowired
    Keesun keesun;

    @Override
    public void run(ApplicationArguments args) {
         postRepository.findAll().forEach(System.out::println);
         System.out.println(keesun.getName());
    }
}