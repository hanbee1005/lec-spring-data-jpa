package com.example.lecspringdatajpa.post;

import java.util.List;

public interface PostCustomRepository {

    List<PPost> findMyPost();
}
