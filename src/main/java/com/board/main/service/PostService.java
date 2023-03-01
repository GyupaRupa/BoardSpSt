package com.board.main.service;

import com.board.main.domain.Post;
import com.board.main.domain.PostDTO;

import java.util.List;

public interface PostService {
    Long post(String writer, String title, String content, Integer boardType);
    List<Post> findAll();
}
