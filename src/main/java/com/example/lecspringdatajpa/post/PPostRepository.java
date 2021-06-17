package com.example.lecspringdatajpa.post;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PPostRepository extends JpaRepository<PPost, Long>, PostCustomRepository<PPost> {
}
