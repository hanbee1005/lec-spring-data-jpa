package com.example.lecspringdatajpa.post;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class PPostRepositoryTest {

    @Autowired
    PPostRepository pPostRepository;

    @Test
    public void crud() {
        PPost post = new PPost();
        post.setTitle("hibernate");
        pPostRepository.save(post);

        pPostRepository.findMyPost();

        pPostRepository.delete(post);
        pPostRepository.flush();
    }

}