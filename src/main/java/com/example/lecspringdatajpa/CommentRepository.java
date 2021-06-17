package com.example.lecspringdatajpa;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface CommentRepository extends MyRepository<Comment, Long> {

    List<Comment> findByCommentContains(String keyword);

    // Pageable은 Sort 기능도 포함되어 있음.
    // List 타입으로 리턴 받을 수 있음. but, 페이징 관련 정보는 사라짐.
    Page<Comment> findByLikeCountGreaterThanAndPostOrderByLikeCountDesc(int likeCount, Post post, Pageable pageable);

    List<Comment> findByLikeCountGreaterThanAndPost(int likeCount, Post post, Sort sort);
}
