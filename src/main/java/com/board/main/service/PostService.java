package com.board.main.service;

import com.board.main.domain.Member;
import com.board.main.domain.Post;
import com.board.main.domain.PostDTO;

import java.util.List;

public interface PostService {
    Long post(Member Author, String title, String content, Integer boardType);

    Post findById(Long id) throws Exception;
    List<Post> findAll();
}
