package com.example.lecspringdatajpa;

import com.example.lecspringdatajpa.lec01.Comment;
import com.example.lecspringdatajpa.lec01.CommentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.lang.Nullable;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.util.List;
import java.util.concurrent.ExecutionException;

import static org.assertj.core.api.Assertions.assertThat;

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
        ListenableFuture<List<Comment>> future = commentRepository.findByCommentContainsIgnoreCase("jpa");

        // Then
        System.out.println("==========================");
        System.out.println("is done? " + future.isDone());

        future.addCallback(new ListenableFutureCallback<List<Comment>>() {
            @Override
            public void onFailure(Throwable ex) {
                System.out.println(ex);
            }

            @Override
            public void onSuccess(@Nullable List<Comment> comments) {
                System.out.println("==========================");
                comments.forEach(System.out::println);
            }
        });
    }

    private void createComment(String comment, int likeCount) {
        Comment comment1 = new Comment();
        comment1.setComment(comment);
        comment1.setLikeCount(likeCount);
        commentRepository.save(comment1);
    }
}