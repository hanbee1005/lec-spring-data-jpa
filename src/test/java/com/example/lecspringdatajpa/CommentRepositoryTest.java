package com.example.lecspringdatajpa;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class CommentRepositoryTest {

    @Autowired
    CommentRepository commentRepository;

    @BeforeEach
    public void makeComments() {
        createComment("Spring Data jpa", 55);
        createComment("JPA test", 100);
    }

    @Test
    public void commentContains() {
        // When
        List<Comment> comments = commentRepository.findByCommentContains("spring");

        // Then
        assertThat(comments.size()).isEqualTo(0);

//        // Given
//        comments = commentRepository.findByCommentContainsIgnoreCase("spring");
//
//        // When
//        assertThat(comments.size()).isEqualTo(1);
    }

    @Test
    public void likeCountGreaterThan() {
        // When
        List<Comment> comments = commentRepository.findByCommentContainsAndLikeCountGreaterThan("Spring", 50);

        // Then
        assertThat(comments.size()).isEqualTo(1);
    }

    @Test
    public void orderByLikeCountDesc() {
        // When
        List<Comment> comments = commentRepository.findByCommentContainsIgnoreCaseOrderByLikeCountDesc("jpa");

        // Then
        assertThat(comments.size()).isEqualTo(2);
        assertThat(comments.get(0).getLikeCount()).isGreaterThan(comments.get(1).getLikeCount());

        assertThat(comments).first().hasFieldOrPropertyWithValue("likeCount", 100);
        assertThat(comments).last().hasFieldOrPropertyWithValue("likeCount", 55);
    }

    @Test
    public void pageCommentContains() {
        // Given
        PageRequest pageRequest = PageRequest.of(0, 10, Sort.by(Sort.Direction.DESC, "likeCount"));

        // When
        Page<Comment> comments = commentRepository.findByCommentContainsIgnoreCase("jpa", pageRequest);

        // Then
        assertThat(comments.getNumberOfElements()).isEqualTo(2);

        assertThat(comments).first().hasFieldOrPropertyWithValue("likeCount", 100);
        assertThat(comments).last().hasFieldOrPropertyWithValue("likeCount", 55);
    }

    @Test
    public void asyncFutureCommentContains() throws ExecutionException, InterruptedException {
        // When
        Future<List<Comment>> future = commentRepository.findByCommentContainsIgnoreCase("jpa");

        // Then
        System.out.println("==========================");
        System.out.println("is done? " + future.isDone());

        List<Comment> comments = future.get();
        comments.forEach(System.out::println);
    }

    private void createComment(String comment, int likeCount) {
        Comment comment1 = new Comment();
        comment1.setComment(comment);
        comment1.setLikeCount(likeCount);
        commentRepository.save(comment1);
    }
}