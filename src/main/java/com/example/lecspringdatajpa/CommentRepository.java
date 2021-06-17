package com.example.lecspringdatajpa;

import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CommentRepository extends MyRepository<Comment, Long> {

    @Query(value = "SELECT * from Comment as c", nativeQuery = true)
    List<Comment> findByTitleContains(String keyword);
}
