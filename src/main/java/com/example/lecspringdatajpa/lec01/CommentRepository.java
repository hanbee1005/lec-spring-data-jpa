package com.example.lecspringdatajpa.lec01;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.List;

public interface CommentRepository extends MyRepository<Comment, Long> {

    List<Comment> findByCommentContains(String keyword);
    // List<Comment> findByCommentContainsIgnoreCase(String keyword);

    List<Comment> findByCommentContainsAndLikeCountGreaterThan(String keyword, int likeCount);

    List<Comment> findByCommentContainsIgnoreCaseOrderByLikeCountDesc(String keyword);

    // Stream 타입을 반환 받을 수도 있음
    // 사용할 때 try-catch 문으로 받아서 사용해야 함
    Page<Comment> findByCommentContainsIgnoreCase(String keyword, Pageable pageable);

    @Async
    ListenableFuture<List<Comment>> findByCommentContainsIgnoreCase(String keyword);
}
