package com.example.lecspringdatajpa.post;

import java.util.List;

public interface PostCustomRepository<T> {

    List<PPost> findMyPost();

    void delete(T entity);
}
