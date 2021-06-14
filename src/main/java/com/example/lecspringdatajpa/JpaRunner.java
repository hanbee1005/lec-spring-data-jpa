package com.example.lecspringdatajpa;

import org.hibernate.Session;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Component
@Transactional
public class JpaRunner implements ApplicationRunner {

    @PersistenceContext
    EntityManager entityManager;  // JPA 핵심 클래스

    @Override
    public void run(ApplicationArguments args) throws Exception {
//        Post post = new Post();
//        post.setTitle("Spring Data JPA 언제 보나...");
//
//        Comment comment = new Comment();
//        comment.setComment("빨리 보고 싶어요.");
//        post.addComment(comment);
//
//        Comment comment1 = new Comment();
//        comment1.setComment("곧 보여드릴게요!");
//        post.addComment(comment1);

        Session session = entityManager.unwrap(Session.class);

        Post post = session.get(Post.class, 1l);
        System.out.println("==============================");
        System.out.println(post.getTitle());

        post.getComments().forEach(c -> {
            System.out.println("================================");
            System.out.println(c.getComment());
        });
    }
}
