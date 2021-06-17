package com.example.lecspringdatajpa.post;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@Transactional
public class PostCustomRepositoryImpl implements PostCustomRepository {

    @Autowired
    EntityManager entityManager;

    @Override
    public List<PPost> findMyPost() {
        return entityManager.createQuery("SELECT p FROM PPost AS p", PPost.class).getResultList();
    }
}
