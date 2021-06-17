package com.example.lecspringdatajpa;

import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CommentRepository extends MyRepository<Comment, Long> {

    @Query("SELECT c from Comment as c")
    List<Comment> findByTitleContains(String keyword);
}
