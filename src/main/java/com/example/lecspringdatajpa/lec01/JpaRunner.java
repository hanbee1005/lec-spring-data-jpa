package com.example.lecspringdatajpa.lec01;

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

    @Override
    public void run(ApplicationArguments args) {
         Post post = new Post();
         post.setTitle("spring");

         Comment comment = new Comment();
         comment.setComment("hello");

         postRepository.save(post);
    }
}